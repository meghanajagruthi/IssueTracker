/**
 * Harsh Verma
 */
$(document).ready(function() {
	initializeTable();
	initializeTotalOfTable();
});

var currencyRenderer = function(instance, td, row, col, prop, value,cellProperties) {
	
	Handsontable.NumericCell.renderer.apply(this, arguments);
	if(cellProperties.readOnly == false){
		$(td).css({
			background : 'rgb(237, 239, 141)'
		});		
	}
	$(td).css({
		textAlign : 'left'
	});
	
	if (!isNaN(value)) {
		try {
			if(prop == "%"){
				td.innerHTML = convertor2Percentage(value);
			}else{
				td.innerHTML = convertor2Currency(value);
			}
		} catch (err) {
			td.innerHTML = value;
		}
	}
};


var data = [ 
         {"DESCRIPTION OF WORK": "TEST LINE 1", 
			"SCHEDULED VALUE": "500000", 
			"FROM PREVIOUS APPLICATION":"",
			"THIS PERIOD":"50000",
			"MATERIALS PRESENTLY STORED":"",
			"TOTAL COMPLETED AND STORED":"50000",
			"%":"10",
			"BALANCE TO FINISH":"450000",
			"RETAINAGE":"5000"},
		{"DESCRIPTION OF WORK": "TEST LINE 2", 
			"SCHEDULED VALUE": "500000", 
			"FROM PREVIOUS APPLICATION":"",
			"THIS PERIOD":"50000",
			"MATERIALS PRESENTLY STORED":"",
			"TOTAL COMPLETED AND STORED":"50000",
			"%":"10",
			"BALANCE TO FINISH":"450000",
			"RETAINAGE":"5000"}];

var columnheader = function(col) {
	switch (col) {
	case 0:
		return "DESCRIPTION OF WORK";
	case 1:
		return "SCHEDULED VALUE";
	case 2:
		return "FROM PREVIOUS APPLICATION";
	case 3:
		return "THIS PERIOD";
	case 4:
		return "MATERIALS PRESENTLY STORED";
	case 5:
		return "TOTAL COMPLETED AND STORED";
	case 6:
		return "%";
	case 7:
		return "BALANCE TO FINISH";
	case 8:
		return "RETAINAGE";
	}
};

var readonlycolumn = [ {
	data : "DESCRIPTION OF WORK",
}, {
	data : "SCHEDULED VALUE",
	renderer : currencyRenderer
}, {
	data : "FROM PREVIOUS APPLICATION",
	renderer : currencyRenderer,
	readOnly : true
}, {
	data : "THIS PERIOD",
	renderer : currencyRenderer
}, {
	data : "MATERIALS PRESENTLY STORED",
	renderer : currencyRenderer
}, {
	data : "TOTAL COMPLETED AND STORED",
	readOnly : true,
	renderer : currencyRenderer
}, {
	data : "%",
	readOnly : true,
	renderer : currencyRenderer
}, {
	data : "BALANCE TO FINISH",	
	renderer : currencyRenderer,
	readOnly : true
}, {
	data : "RETAINAGE",
	readOnly : true,
	renderer : currencyRenderer
} ];

var Rowheader = function(row) {
	switch (row) {
	case 0:
		return "Total";
	}
};

/**
 * Intializing Handson Table
 * changes[0][0] --> gives row no.
 * changes[0][1] --> gives Column Name
 * changes[0][2] --> gives Previous Cell Value
 * changes[0][3] --> gives Current Cell Value
 */
var sovTable;
function initializeTable(){
	
	sovTable = $("#sovHandsonTable");
	sovTable.handsontable({
		data : data,
		colWidths : [ 140, 110, 110, 110, 150, 150, 70, 110, 90 ],
		minSpareRows : 0,
		colHeaders : columnheader,
		rowHeaders: false,
		contextMenu : false,
		nativeScrollbars : true,
		columns : readonlycolumn,
		cells : function(r, c, prop) {
			
		},
		beforeChange : function(changes, source) {
			
			if (source === 'convert')
				return;
			if(changes[0][1] != "DESCRIPTION OF WORK"){
				if (isNaN(changes[0][3]) && changes[0][3] != "") {
					changes[0][3] = changes[0][2];
					alert("Please enter a valid Number.");
					return;
				}
			}
			
//			ht3.setDataAtRowProp(changes[0][0], 'THIS PERIOD',"9","convert");
//			alert(ht3.getDataAtRowProp(changes[0][0],'FROM PREVIOUS APPLICATION'));
			if(changes[0][1] == "THIS PERIOD"){
				
				var preApp = convertor2Int(ht3.getDataAtRowProp(changes[0][0],'FROM PREVIOUS APPLICATION'));
				var thisApp = convertor2Int(changes[0][3]);
				var materialCost = convertor2Int(ht3.getDataAtRowProp(changes[0][0],'MATERIALS PRESENTLY STORED'));
				
				var finalCost = preApp + thisApp + materialCost;
				ht3.setDataAtRowProp(changes[0][0], 'TOTAL COMPLETED AND STORED',convertor2Currency(finalCost),"convert");
				
				var schVal = convertor2Int(ht3.getDataAtRowProp(changes[0][0],'SCHEDULED VALUE'));
				var percentage = convertor2Int((finalCost / schVal)*100).toFixed(2);
				if(isNaN(percentage)){
					percentage = 0;
				}
				ht3.setDataAtRowProp(changes[0][0], '%',convertor2Percentage(percentage),"convert");
				
				var balFinishedVal = schVal-finalCost;
				ht3.setDataAtRowProp(changes[0][0], 'BALANCE TO FINISH',convertor2Currency(balFinishedVal),"convert");
				
				var retainage = (((preApp + thisApp)*10)/100)+((materialCost*10)/100);
				ht3.setDataAtRowProp(changes[0][0], 'RETAINAGE',convertor2Currency(retainage),"convert");
				
				totalOffVal(ht3,changes[0][0],null,changes[0][3],null);
				initializeTotalOfTable();
			}else if(changes[0][1] == "MATERIALS PRESENTLY STORED"){
				
				var preApp = convertor2Int(ht3.getDataAtRowProp(changes[0][0],'FROM PREVIOUS APPLICATION'));
				var thisApp = convertor2Int(ht3.getDataAtRowProp(changes[0][0],'THIS PERIOD'));
				var materialCost = convertor2Int(changes[0][3]);
				
				var finalCost = preApp + thisApp + materialCost;
				ht3.setDataAtRowProp(changes[0][0], 'TOTAL COMPLETED AND STORED',convertor2Currency(finalCost),"convert");
				
				var schVal = convertor2Int(ht3.getDataAtRowProp(changes[0][0],'SCHEDULED VALUE'));
				var percentage = convertor2Int((finalCost / schVal)*100).toFixed(2);
				if(isNaN(percentage)){
					percentage = 0;
				}
				ht3.setDataAtRowProp(changes[0][0], '%',convertor2Percentage(percentage),"convert");
				
				var balFinishedVal = schVal-finalCost;
				ht3.setDataAtRowProp(changes[0][0], 'BALANCE TO FINISH',convertor2Currency(balFinishedVal),"convert");
				
				var retainage = (((preApp + thisApp)*10)/100)+((convertor2Int(changes[0][3])*10)/100);
				ht3.setDataAtRowProp(changes[0][0], 'RETAINAGE',convertor2Currency(retainage),"convert");
				
				totalOffVal(ht3,changes[0][0],null,null,changes[0][3]);
				initializeTotalOfTable();
			}else if(changes[0][1] == "SCHEDULED VALUE"){
				
				var storedVal = convertor2Int(ht3.getDataAtRowProp(changes[0][0],'TOTAL COMPLETED AND STORED'));
				var schVal = convertor2Int(changes[0][3]);
				
				var percentage = convertor2Int((storedVal / schVal)*100).toFixed(2);
				var balFinishedVal = schVal-storedVal;
				
				if(isNaN(percentage)){
					percentage = 0;
				}
				ht3.setDataAtRowProp(changes[0][0], '%',convertor2Percentage(percentage),"convert");
				ht3.setDataAtRowProp(changes[0][0], 'BALANCE TO FINISH',convertor2Currency(balFinishedVal),"convert");

				totalOffVal(ht3,changes[0][0],changes[0][3],null,null);
				initializeTotalOfTable();
				
			}
		},afterSelection : function(changes, source) {
			
		}
	});
	
	var ht3 = sovTable.handsontable('getInstance');
	totalOffVal(ht3,null,null);

}

var totalSchVal ,totalPreApp ,totalThisPeriod ,totalMatStored ,totalCompStored ,totalFinishedBal ,totalRetainaige;

function totalOffVal(ht3,rowId,currVal,thisPeriod,matStored){
	totalSchVal = totalPreApp = totalThisPeriod = totalMatStored = totalCompStored = totalFinishedBal = totalRetainaige = 0;
	var totalRows = ht3.countRows();
	
	for(var i = 0;i<totalRows;i++){
		
		if(rowId == i && rowId !=  null && currVal !=  null && matStored ==  null){
			totalSchVal += convertor2Int(currVal);
		}else{
			totalSchVal += convertor2Int(ht3.getDataAtRowProp(i,'SCHEDULED VALUE'));
		}
		
		totalPreApp += convertor2Int(ht3.getDataAtRowProp(i,'FROM PREVIOUS APPLICATION'));
		
		if(rowId == i && rowId !=  null && thisPeriod !=  null && currVal ==  null){
			totalThisPeriod += convertor2Int(thisPeriod);
		}else{
			totalThisPeriod += convertor2Int(ht3.getDataAtRowProp(i,'THIS PERIOD'));
		}
		
		if(rowId == i && rowId !=  null && matStored !=  null && thisPeriod ==  null){
			totalMatStored += convertor2Int(matStored);
		}else{
			totalMatStored += convertor2Int(ht3.getDataAtRowProp(i,'MATERIALS PRESENTLY STORED'));
		}
		
		totalCompStored += convertor2Int(ht3.getDataAtRowProp(i,'TOTAL COMPLETED AND STORED'));
		totalFinishedBal += convertor2Int(ht3.getDataAtRowProp(i,'BALANCE TO FINISH'));
		totalRetainaige += convertor2Int(ht3.getDataAtRowProp(i,'RETAINAGE'));
	}
	
}
/**
 * Create new row in table
 */
function createRowInTable(){
	var instance = sovTable.handsontable('getInstance');
	instance.alter('insert_row', null, 1);
}

function deleteRowInTable(){
	
	var delRow = sovTable.handsontable('getSelected')[0];
	alert(delRow);
//	instance.alter('remove_row', 0, 1);
}
var dataTotal = [];

var total = function(col) {
	switch (col) {
	case 0:
		return "Total";
	case 1:
		return convertor2Currency(totalSchVal);
	case 2:
		return convertor2Currency(totalPreApp);
	case 3:
		return convertor2Currency(totalThisPeriod);
	case 4:
		return convertor2Currency(totalMatStored);
	case 5:
		return convertor2Currency(totalCompStored);
	case 6:
		return "";
	case 7:
		return convertor2Currency(totalFinishedBal);
	case 8:
		return convertor2Currency(totalRetainaige);
	}
};

/**
 * Handson Table for show total
 */
function initializeTotalOfTable(){
	
	var sovTotalTable = $("#sovTotalHandsonTable");
	sovTotalTable.handsontable({
		data : dataTotal,
		colWidths : [140, 110, 110, 110, 150, 150, 70, 110, 90 ],
		minSpareRows : 0,
		colHeaders : total,
		rowHeaders: false,
		contextMenu : false,
		nativeScrollbars : true,
		columns : readonlycolumn
	});
}

function removeDollarComma(value) {
	var v = value.replace(/,/g,"").replace("$","");
	if(v.indexOf("-") > -1 ){
		v = v.replace("-","");
	}
	return v;
}

function convertor2Int(value) {
	var v = value == null || value == "" ? 0 : value;
	return +v;
}

function convertor2Currency(value) {
	value = convertor2Int(value);
	var num = '$' + value.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
	return num;
}

function convertor2Percentage(value) {
	value = convertor2Int(value);
	var num = value.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + '%';
	return num;
}
