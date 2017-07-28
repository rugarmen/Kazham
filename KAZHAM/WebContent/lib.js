
rootKAZHAM = '/KAZHAM/'; 
tipoArchivoTXT = 'ARC';

function f_render_estado(v){ 
	return '<img src="' + rootKAZHAM + 'resources/images/icons/check_' + (v=='S'?'true':'false') + '.png' + '">';
}

function f_render_origen(v){
	return '<img title="' + v + '" src="' + rootKAZHAM + 'ext/images/icons/' + (v=='FORMULA'?'formula.gif':(v=='PLAN'?'coments.png':'restruccion.png'))  + '">';
}

function convertARRAY(store){
	var arrItems = [];
	var arrStore = store.data.items;
	
	for(var i=0 ; i<arrStore.length; i++){
		arrItems.push(arrStore[i].json);
	}
	return arrItems;
}


function validaFecha(ctrlFechaini, ctrlFechafin){
	var strMsg = 'OK';
	
	if(convertFecha(ctrlFechaini.value) > convertFecha(ctrlFechafin.value)){
		strMsg = 'La Fecha Inicial no puede ser mayor que la Fecha Final';
	}
	
	return strMsg;
}

function convertFecha(valor){
	var strFecha ;
		strFecha = valor.split('/')[1] + '/' + valor.split('/')[0] + '/' + valor.split('/')[2];
		strFecha = new Date(strFecha);
	return strFecha;
}
/*FIN ACUERDO*/

