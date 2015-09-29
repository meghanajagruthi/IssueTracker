/**
 * Anantha Meghanajagruthi
 */



var resultant="";
var requiredDates=[];
function generateData(dataForLineCharts)
{
	requiredDates.sort(date_sort_asc);
	 var flag=0;
	   var k=0;
	  var temp=[];
	   for(var i=0;i<requiredDates.length;i++)
	    {
		   flag=0;
		  for(var  j=0;j<dataForLineCharts.length;j++)
			  {
			
			    if(requiredDates[i]==moment(new Date(dataForLineCharts[j][1])).format("MM-DD-YYYY"))
			    	{
			    	  flag=1;
			    	  k=j;
			    	  break;
			    	}
			    
			  }
		  if(flag==1){
			  temp.push([dataForLineCharts[k][0]]);
		  }
		  else{
			  temp.push(0);
		  }
	       
	    }
	return temp;
	
}
function   getDashBoardCharts()
{
	if(window.Highcharts === undefined) //to load the scripts of high charts only once
		{
		$('<script>', {src: 'assets/js/highcharts.js'}).appendTo('head');
		$('<script>', {src: 'assets/js/drilldown.js'}).appendTo('head');
		$('<script>', {src: 'assets/js/exporting.js'}).appendTo('head');
		}
	resultant="";
	requiredDates=[];
	var tempArray=[];
	var tempArray2=[];	
	$("#loadingModalDicId").modal("show");
	if($('#timeToSearchInDashboard').val()=='other')
	{
		loadLineCharts($('#applicationToSearchInDashboard').val(),$('#issuesToSearchInDashboard').val(),$('#daysToSearchInDashboard').val());
	}
	else{
	loadLineCharts($('#applicationToSearchInDashboard').val(),$('#issuesToSearchInDashboard').val(),$('#timeToSearchInDashboard').val());
	}
     tempArray=generateData(resultant.dataForLineCharts);
     tempArray2=generateData(resultant.dataForLineChartsResolved);
   
   initializePieCharts();
   $('#container1').highcharts({
	   chart:{
		 type:'area'  
	   },
	   
       title: {
           text: 'Created vs Resolved issues',
           x: -20 //center
       },
       xAxis: {
           categories: requiredDates
       },
       yAxis: {
    	  
           title: {
               text: 'Issues count'
           },
           plotLines: [{
               value: 0,
               width: 1,
               color: '#808080'
           }]
       },
       tooltip: {
           
       },
       legend: {
           layout: 'vertical',
           align: 'right',
           verticalAlign: 'middle',
           borderWidth: 0
       },
       credits: {
    	      enabled: false
    	  },
       series: [{
           name: 'Created',
           data: tempArray,
       }, {
           name: 'Resolved',
           data: tempArray2
       }]
   });
	$("#loadingModalDicId").modal("hide");
}


var date_sort_asc = function (date1, date2) {
//	  // This is a comparison function that will result in dates being sorted in
//	  // ASCENDING order. As you can see, JavaScript's native comparison operators
//	  // can be used to compare dates. This was news to me.
	  if (date1 > date2) return 1;
	  if (date1 < date2) return -1;
	  return 0;
	};
function loadLineCharts(appId,issueType,noOfDays)
{
	getDataForLineCharts(appId,issueType,noOfDays);
	var requireDates=[];
   resultant=JSON.parse(loadDataforLineCharts);
  
	for(var i=0;i<resultant.dataForLineCharts.length;i++)
		{
		   requireDates.push(resultant.dataForLineCharts[i][1]);
		}
	for(var i=0;i<resultant.dataForLineChartsResolved.length;i++)
	{
	   if(requireDates.indexOf(resultant.dataForLineChartsResolved[i][1])==-1)
	     requireDates.push(resultant.dataForLineChartsResolved[i][1]);
	}
	
		requireDates.sort(date_sort_asc);
		$.each(requireDates, function(i, el){
		    if($.inArray(el, requiredDates) === -1) requiredDates.push(moment(new Date(el)).format("MM-DD-YYYY"));
		});
		
	
}


function getDataForLineCharts(appId,issueType,noOfDays)
{
	
	var applId=JSON.stringify(appId);
	var days=-1;
	if(noOfDays!="entire")
	{
		days=noOfDays;
	}
	
	var date={"appId":applId,"issueType":issueType,"days":days};
	
	$.ajax({
		url:"getDataForLineCharts",
		type:"POST",
		data:date,
		async:false,
		success:function(result)
		{
			loadDataforLineCharts=result;
			
		}
	});
}


var applicationList=[];
var drilldownlevel1=[];
var statusResult=[];
var sresult="";


function initializePieCharts()
{
	$.ajax({
		url:"getAllAppsForDashboard",
		type:"POST",
		async:false,
		success:function(tempResult)
		{
			
			var Tresult=JSON.parse(tempResult);
			drilldownlevel1=[];	
			for(var i=0;i<Tresult.applicationList.length;i++)
			{	
				if($("#applicationToSearchInDashboard").val()[0]==Tresult.applicationList[i].applicationSno)
					{
					  var temp= JSON.parse(findStatusByApp($("#applicationToSearchInDashboard").val()));
					  drilldownlevel1.push([Tresult.applicationList[i].applicationName,temp,Tresult.applicationList[i].applicationSno]);
						break;  
					}
				  
				}
			
		}
	});
	
	var statusNames="";
	$.ajax({
		url:"getAllStatus",
		type:"POST",
		async:false,
		success:function(statusList)
		{
			
			statusNames=JSON.parse(statusList);
			
		}
	});
	var drilldownSeries1=[];
	var drilldownSeries=[];
	for(var t=0;t<statusNames.statusList.length;t++){
	 drilldownSeries1.push({
	        name: drilldownlevel1[0][1][t].statusName,
	        id: drilldownlevel1[0][1][t].statusName,
	        drilldown:drilldownlevel1[0][2]+" "+drilldownlevel1[0][1][t].statusName,
	        y:parseInt(drilldownlevel1[0][1][t].statusValue)
	    });
	}
	
	
	
	
	 var drilldownLevel2=[];
	 var noOfDays=-1;
	 if($('#timeToSearchInDashboard').val()=='other')
		{
		 noOfDays=$('#daysToSearchInDashboard').val();
		}
		else{
			if($('#timeToSearchInDashboard').val()!="entire")
			{
			noOfDays=$('#timeToSearchInDashboard').val();
			}
		}
	 
	 var appId=JSON.stringify($("#applicationToSearchInDashboard").val());
	 for(var i=0;i<statusNames.statusList.length;i++){
			var data={
					"appId":appId,
					"status":statusNames.statusList[i],
					"noOfDays":noOfDays
			};
			
			$.ajax({
				url:"getIssueTypesForDashboard",
				method:"POST",
				data:data,
				async:false,
				success:function(result)
				{
					
					drilldownLevel2.push([$("#applicationToSearchInDashboard").val()[0],statusNames.statusList[i],JSON.parse(result)]);
					
				}
			});
		
		}
	 $.each(drilldownLevel2,function(key,value){
	 drilldownSeries.push({
	        name: drilldownLevel2[key][1],
	       id: drilldownLevel2[key][0][0]+ " "+drilldownLevel2[key][1] ,
	        data: [
	               [drilldownLevel2[key][2][0].IssueTypeName,parseInt(drilldownLevel2[key][2][0].IssueTypeValue)],
	               [drilldownLevel2[key][2][1].IssueTypeName,parseInt(drilldownLevel2[key][2][1].IssueTypeValue)],
	               [drilldownLevel2[key][2][2].IssueTypeName,parseInt(drilldownLevel2[key][2][2].IssueTypeValue)]
	             ]
	    
	    });
	 });
	 $('#container').highcharts({
		    chart: {
		        type: 'pie'
		    },
		  
		    title: {
		        text: 'Summarized Application data.'
		    },
		    tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span>{point.name}</span>: <b>{point.y}</b><br/>'
            },
		    credits: {
			      enabled: false
			  },
		    series: [{
		        type: 'pie',
		        name: 'Status',
		        data: drilldownSeries1
		    }],
		    drilldown: {
		    	
		    	series:drilldownSeries
		    }
		});

}






function assign(result)
{
	statusResult.push(result);

}


function findStatusByApp(appId){
	var noOfDays=-1;
	 if($('#timeToSearchInDashboard').val()=='other')
		{
		 noOfDays=$('#daysToSearchInDashboard').val();
		}
		else{
			if($('#timeToSearchInDashboard').val()!="entire")
			{
			noOfDays=$('#timeToSearchInDashboard').val();
			}
		}
	 var applicationToSearchString = "";
	 
		if(appId != undefined && appId != null){
			for(var len = 0;len < appId.length;len++){
				if(len == appId.length - 1){
					applicationToSearchString += appId[len];
				} else {
					applicationToSearchString += appId[len]+",";
				}
			}
		}
	 
	var appData={"appId":applicationToSearchString,"noOfDays":noOfDays};
	$.ajax({
		url:"getTotalStatusByApp",
		type:"POST",
		async:false,
		data:appData,
		success:function(result)
		{
			
			sresult=result;
			
			
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			  console.log(textStatus);
			  console.log(errorThrown);
			}
  });
	
	return sresult;
}
