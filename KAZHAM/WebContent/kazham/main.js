JS.include(rootSAS+'mant/mntAcercade.js');
JS.include(rootSAS+'mant/cambioClave.js');

Ext.namespace('Ext.sas');

Ext.sas.main = function(config){

    return new Ext.Viewport(Ext.apply({
        layout:'border',

        initComponent : function(){

        	this.initSession=true;
        	this.paneles=[];
        	        	
            this.accordion = new Ext.Panel({
                title        : 'Módulos',
        		layout       : 'accordion',
                region       : 'center',
                items : []
            });

        	this.resumen = new Ext.Panel({
                title        : 'Descripción',
        		layout       : 'fit',
        		region       : 'south',
                collapsible  : true,
                collapseMode : 'mini',
        		height       : 100,
        		bodyStyle : 'background-color:#eaeaea;',
        		items : [
        		  LABEL('<div id="idTitle" style="font-size:10px;font-weight:bold;padding:5px 15px 0px 15px;"></div>'),
        		  LABEL('<div id="idBody" style="font-size:10px;padding:0px 15px 5px 15px;"></div>')
        		]
        	});     
        	
        	this.west = new Ext.Panel({
        		header       : false,
        		layout       : 'border',
        		region       : 'west',
                split        : true,
                width        : 210,
                resizable    : false,
                minSize      : 200,
                maxSize      : 200,
                //collapsible  : true,
                //collapseMode : 'mini',
                margins      : '0 0 5 5',
                cmargins     : '0 5 5 5',
                layoutConfig : {animate:true},
                bbar : new Ext.Toolbar({
                	iconCls:'bt-toolbar',
                	items: [
                	    {
                	    	iconCls : 'x-calendario',
                	    	tooltip : 'Fecha Actual',
                	    	id      : 'tbFrmeWorkFecha',
                	    	text    : ' ' + new Date().format('d/m/Y')
                	    },
                	    '-',
                	    {
                	    	id      : 'tbFrmeWorkClock',
                	    	tooltip : 'Hora Actual',
                	    	text    : new Date().format('g:i:s A')
                	    },
                	    '-',
                	    '->',
                	    {
                	    	iconCls : 'tb-acercade',
                	    	tooltip : 'Acerca de SAS',
                	    	anchor  : '100%',
                	    	handler : this.loadAcerdade,
                	    	scope: this
                	    },
                	    '-',
                	    {
                	    	iconCls : 'tb-f1',
                	    	tooltip : 'Ayuda SAS',
                	    	anchor  :'100%',
                	    	handler : this.loadAyuda,
                	    	scope: this
                	    }]
                }),
                items : [this.accordion, this.resumen]
        	});                  
            
            this.headerpanel = new Ext.Panel({
                layout       : 'table',
                region       : 'center',
                border       : false,
                height       : 80,  
                layoutConfig : {
                	columns : 2,
                    tableAttrs : {
                        style : {width:'100%'}
                    }
                },
                html : 
                	'<table background="sas/main/header/cabecerafin.gif" border="0" cellpadding="0" cellspacing="0" height="100" width="100%">'+ 
                	'	<tr height="100">'+ 
                	'		<td height="100" width="223" background="sas/main/header/logo.gif">&nbsp;'+ 
                	'		</td>'+ 
                	'		<td height="100" width="455" background="sas/main/header/cabecera.gif">&nbsp;'+ 
                	'		</td>'+ 
                	'		<td height="100">&nbsp;'+ 
                	'		</td>'+ 
                	'	</tr>'+ 
                	'</table>',
                defaults : {frame:true,header:false,border:true,baseCls:'x-plain'}
                
            });
			
            this.btnLogin = new Ext.Button({
            	id: 'btnLog',
            	text: '<b>'+'Iniciar SesiónYY'+'</b>',
            	iconCls: 'tb-login',
            	renderTo: 'idDivButton',
            	handler: this.loadSession,
            	scope: this
            }); 
            
            this.btnCambioClave = new Ext.Button({
            	id: 'btnClave',
            	text: '<b>'+'Cambio Clave'+'</b>',
            	iconCls: 'tb-login',
            	renderTo: 'idDivClave',
            	handler: this.loadCambiarClave,
            	scope: this
            }); 
            
            this.header = {
                region   : 'north',
                layout   : 'border',
                width    : 200,
                height   : 100,
                header   : false,
                border   : false,
                margins  : '0 0 5 0',
                cmargins : '0 5 5 0',
                items : [
                    this.headerpanel        		
                ]
            };
            
            this.tabpanel = new Ext.TabPanel({
                title           : 'Opciones',
                region          : 'center',
                resizeTabs      : true,
                border          : true,
                margins         : '0 5 5 0',
                activeTab       : 0,
                deferredRender  : false,
                resizeTabs      : false,
                enableTabScroll : true,
                html            : '<div id="start-div" style="padding:40px;"><div style="float:left;"><img src="sas/main/logo_division.png"></div>'+
                				  '<div style="margin-left:100px;"><h6>Bienvenido!</h6>'+
                				  '<p>Al sistema integrado de Administración de Seguros de Rimac.</p>'+
                				  '<p>Seleccione una opción del lado izquierdo para empezar.</p></div></div>',
                defaults        : {autoScroll:true},
                plugins         : [new Ext.ux.TabScrollerMenu({maxText: 40, pageSize: 10}), new Ext.ux.TabCloseMenu({})],
                listeners       : {
                	'render': {
                		fn: function(){
                			Ext.TaskMgr.start({
                				run: function(){
                					Ext.getCmp('tbFrmeWorkFecha').setText( new Date().format('d/n/Y') );
                					Ext.getCmp('tbFrmeWorkClock').setText( new Date().format('g:i:s A') );
                				},
                				interval: 1000
                			});
                			
                			// [INI - HA - 13/08/2013]
                			var ejecutar = false;
                			Ext.TaskMgr.start({
                				run: function() {
                					var nombre = 'KAZHAM_CODUSUARIO_COOKIE';
                					var userCookie='';
                					var comienzo;
                					var cantidad;
                					if(ejecutar) {
		                				var cookies=document.cookie;
		                				var userLogin = Ext.get('idDivUser').dom.innerHTML.toString();
		                				if(userLogin.length>1) userLogin = userLogin.substr(2, userLogin.length);
		                				
		                				if(cookies) {
		                					comienzo = cookies.indexOf(nombre);
		                					if(comienzo != -1){
		                						comienzo=comienzo+nombre.length+1;
		                        				cantidad=cookies.indexOf("; ", comienzo)-comienzo; 
		                        				if(cantidad<=0) cantidad=cookies.length;
		                        				userCookie = cookies.substr(comienzo, cantidad);
		                					}
		                				}
		                				
	                					if(userCookie === null || userCookie==='') {
	                						window.location='sessionCerrada.html';
	                					} else if(userCookie != userLogin) {
	                						Ext.Msg.alert('Validacion', 'Ha iniciado una sessión con otro usuario');
	                					}
                					}
                					
                					ejecutar = true;
                				},
                				interval: 1000
                			});
                			// [FIN - HA - 13/08/2013]
                		}
                	}
                },
                items : []
            });
            
            this.items=[this.header, this.west, this.tabpanel];
            Ext.Viewport.prototype.initComponent.call(this);
            
            this.initKeyMap();
            this.init.defer(100, this);
            //INI - DSM 14/01/2015
            this.validarOportunidad();
            //FIN - DSM 14/01/2015
        },
        
        init : function(){
        	this.loadModulos.defer(100, this);
        },
        
        initKeyMap : function(){
        	new Ext.KeyMap(Ext.getDoc(), [{
	        		key: Ext.EventObject.F1,
	        		fn: function(scope, event){
	        			this.loadAyuda();
	        		},
	        	    scope: this
        		}]
        	);
        },
        
        loadAcerdade : function(){
        	if(!this.dlgViewAcercade){
        		this.dlgViewAcercade = sas.mant.mntacercade({parent: this});
        	}
        	this.dlgViewAcercade.show();
        },
       
        loadCambiarClave : function(){
        	var userCookie  = "";
        	var codusuario = "";
        	if (this.dlgViewCambiarClave) {
				this.dlgViewCambiarClave.destroy();
			}
        	
        		var nombre = 'KAZHAM_CODUSUARIO_COOKIE';
        		var cookies=document.cookie;
				var userLogin = Ext.get('idDivUser').dom.innerHTML.toString();
				if(userLogin.length>1) userLogin = userLogin.substr(2, userLogin.length);
				
				if(cookies) {
					comienzo = cookies.indexOf(nombre);
					if(comienzo != -1){
						comienzo=comienzo+nombre.length+1;
        				cantidad=cookies.indexOf("; ", comienzo)-comienzo; 
        				if(cantidad<=0) cantidad=cookies.length;
        				userCookie = cookies.substr(comienzo, cantidad);
					}
				}
				
				if(userCookie === null || userCookie==='') {
					window.location='sessionCerrada.html';
				} else if(userCookie != userLogin) {
					Ext.Msg.alert('Validacion', 'Ha iniciado una sessión con otro usuario');
				}
				
				
        		this.dlgViewCambiarClave = sas.mant.cambioclave({parent: this});
        		codusuario =userCookie;
        	this.dlgViewCambiarClave.show(codusuario);

        },
        
        
       
        loadAyuda : function(){
    		if (this._f1) window.open('/AYUDA/help/'+this._f1, '_blank', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=500,height=600');
    		else{ 
        		Ext.Msg.alert('Mensaje', 'Ayuda no configurada.', function(){
        		}, this);
    		}
        },
        
        loadModulos : function(){
        	callServer(rootSAS+'sas.do?method=lstAplicaciones', {}, function(v, j) {
            	if (j.origen=='Success'){
                    var ar = eval(j.data);
    	            for(var i = 0; i < ar.length; i++){
    	        	  this.accordion.add(this.createMenuModulo(ar[i].codaplicacion,ar[i].descripcion));
    	            }                
    	            this.loadSession(j.user);
    	            this.accordion.doLayout();
            	}else{
            		Ext.Msg.alert('Mensaje', 'Usuario o clave invalidos. <BR> Presione <B>Aceptar</B> para cerrar la ventana.', function(){
            			window.close();
            		}, this);
            	}

            }, this, function(msg, j) {
                Ext.Msg.error(msg);
            });

        },
        
        loadSession : function(user){
        	if (typeof user == 'object'){
        		if (this.initSession===true){
            		Ext.Msg.alert('Mensaje', 'Acceso no permitido. <BR> Presione <B>Aceptar</B> para cerrar la ventana.', function(){
            			window.close();
            		}, this);
        		}else{
            		Ext.Msg.alert('Mensaje', 'Sesión finalizada. <BR> Presione <B>Aceptar</B> para cerrar la ventana.', function(){
            			 var dominio = {
             	        		SCHEME:'http:',
             	        		SERVERNAME:location.hostname,
             	        		SERVERPORT:location.port
             	        };      
                        callServer(rootSAS+'sas.do?method=closeSession', dominio, function(v, j) {
                        	//this.location.href = v;
                        	document.location.href=v;
                        	this.clearModulos();
                    		window.close();
                        }, this, function(msg, j) {
                            Ext.Msg.error(msg);
                        });            			
            			//window.close();
            		}, this);
        		}
        		return false;
        	}else{
            	if (this.initSession===true){
            		this.initSession=false;
            	    Ext.getCmp('btnLog').setText('<b>'+'Cerrar Sesión'+'</b>');
            	    Ext.getCmp('btnLog').setIconClass('tb-logout');
            		Ext.get('idDivTitle').dom.innerHTML='Sistema de Administración de Seguros - SAS';
            		Ext.get('idDivUser').dom.innerHTML=': ' + user;
            		//this.loadModulos(p);
            	}else{
            		this.initSession=true;
            		Ext.getCmp('btnLog').setText('<b>'+'Iniciar SesiónXX'+'</b>');
            		Ext.getCmp('btnLog').setIconClass('tb-login');
            		Ext.get('idDivTitle').dom.innerHTML='';
            		Ext.get('idDivUser').dom.innerHTML='';
            		SysConfig = {};
            		 var dominio = {
          	        		SCHEME:'http:',
          	        		SERVERNAME:location.hostname,
          	        		SERVERPORT:location.port
          	        };    
                    callServer(rootSAS+'sas.do?method=closeSession', dominio, function(v, j) {
                		this.clearModulos();
                    }, this, function(msg, j) {
                        Ext.Msg.error(msg);
                    });
            	}        		
        	}
        },
        
        clearModulos : function(){
        	this.accordion.removeAll(true);
        	this.accordion.doLayout();
    		Ext.Msg.alert('Mensaje', 'Sesión finalizada. <BR> Presione <B>Aceptar</B> para cerrar la ventana.', function(){
    			window.close();
    		}, this);
        	//location.reload(true);
        },
        
        loadDiv : function(){
    	   	document.write('<div style="position:absolute;top:8px;left:226px;">');
    	   	document.write('	<div id="idDivTitle" style="sans-serif;color:#555555;font-size:30px;font-weight:bold;"></div>');
    	   	document.write('</div>');
    	   	document.write('<div style="position:absolute;top:64px;left:301px;">');
    	   	document.write('	<div id="idDivUser" style="letter-spacing:1px;font-family:arial,sans-serif;color:#ffffff;font-size:11px;font-weight:bold;"></div>');
    	   	document.write('</div>');
			document.write('<div style="position:absolute;top:60px;right:110;>');
    	   	document.write('	<div id="idDivClave"></div>');
    	   	document.write('</div>');
    	   	document.write('<div style="position:absolute;top:60px;right:10px;">');
    	   	document.write('	<div id="idDivButton"></div>');
    	   	document.write('</div>');
    		
        },

        createMenuModulo: function(pid, ptitle){
        	var jMod = {
            	id         : pid,
            	title      : ptitle,
            	layout     : 'fit',
    	    	region     : 'center',
            	items      : [ this.createMenuOpcion(pid) ]};
        	return jMod;
        },

        createMenuOpcion: function(pid){
        	var treeView = new Ext.tree.TreePanel({
    	    	region       : 'center',
    	    	split        : true,
    	    	autoScroll   : true,
    	    	border       : false,
    	    	enableDD     : false,
    	    	rootVisible  : false,
    	    	lines        : true,
    	    	//singleExpand : true,
    	    	useArrows    : true,
    	    	loader       : new Ext.tree.TreeLoader({dataUrl:rootSAS+'sas.do?method=lstOpciones&user='+SysConfig.nomUsuario+'&nodo='+pid}), 
    	    	root         : new Ext.tree.AsyncTreeNode()
	        });
        	
        	treeView.on('afterlayout', function(){
        		this.root.select();
        	});
        	
	        treeView.on('click', function(n){
	        	this._f1="";
	        	var sn = treeView.selModel.selNode || {};
	        	if(n.leaf && n.id && sn.id){
	        		var _v = '';
	        		for(var x=0;x<n.getPath().split('/').length;x++){
	        			if (x>1){
	        				_v = _v + '-' + treeView.getNodeById(n.getPath().split('/')[x]).text;
	        			}
	        		}
	        		
	        		Ext.fly('idTitle').dom.innerHTML = n.attributes.titulo||n.attributes.text||'N/A';
	        		Ext.fly('idBody').dom.innerHTML = n.attributes.dsc||'';
	        		this._f1=n.attributes.f1;
	        		
					//[INI - EHUAMANI - 08/04/2015]
                	if(n.attributes.indNoDisp === 'S'){
                		this.n = n;
                		this.treeView = treeView;
                		this.opcion = 'S';
    	        		//callServer(rootSAS+'sas.do?method=obtMsgNoDisponibilidad', {}, function(v, j){
                		callServer(rootSAS+'sas.do?method=obtMsgNoDisponibilidad', {indopcion:this.opcion}, function(v, j){
    						if(j.origen == 'success'){
    							v = Ext.decode(v);
    							if(v.msgnodisponibilidad !== ''){
    								//Ext.Msg.alert('Mensaje', v.msgnodisponibilidad);
									message('Mensaje', v.msgnodisponibilidad, '', { scope: this, type: 'info' });
    								return;
    							}else{
    								this.loadTab(this.n,this.treeView);
    							}
    						}
    					},this, function(msg, j){
    					    	Ext.Msg.error(msg);
    					});
                	}else{
                		this.loadTab(n,treeView);
                	}
                	
                	/*var w={
                		id       : n.attributes.id,
                		ns       : n.attributes.ns,
                		titulo   : n.attributes.titulo || n.attributes.text,
                		mod      : n.attributes.mod,
                		url      : n.attributes.url,
                		ruta     : n.attributes.ruta,
                		iconCls  : n.attributes.iconCls,
                		cfg      : Ext.decode(n.attributes.cfg),
                		dsc      : n.attributes.dsc,
                		f1       : n.attributes.f1,
                		panel    : n.attributes.panel || 'Panel',
                		closable : true
                	};
                	
//                	window.open("http://rimachome");
                	if (w.id == 6749){
                		window.open(w.url);
                	}else{
                		this.addTab(w);
                	}

                	
                	var r = '', opcc = '';
	        		for (var x = 2; x<n.getPath().split('/').length;x++)
	        		{
	        			opcc = n.getPath().split('/')[x];
	        			r = r + '/' + treeView.getNodeById(n.getPath().split('/')[x]).text;

	        		};
	        		
	        		var codaplicacion= '';
	        		var dscaplicacion = n.attributes.mod;
	        		var codopcion = opcc;
	        		var dscopcion = r;
	        		var p = {codaplicacion:codaplicacion,dscaplicacion:dscaplicacion,codopcion:codopcion,dscopcion:dscopcion};
	                
	         		var par = Ext.apply(p);

	         		callServer(rootSAS + 'sas.do?method=regUsuarioxOpcion',par, function(v, j){
	         			
	         			},this, 
	         			function(msg, j){
	         				Ext.Msg.error(msg);
	         			} 
		    		); 
		    		
                	*/
					//[FIN - EHUAMANI - 08/04/2015]
	        	}
	        }, this);
	        return treeView;
        },		
		//[INI - EHUAMANI - 08/04/2015]
        loadTab : function(n,treeView){

        	var w={
        		id       : n.attributes.id,
        		ns       : n.attributes.ns,
        		titulo   : n.attributes.titulo || n.attributes.text,
        		mod      : n.attributes.mod,
        		url      : n.attributes.url,
        		ruta     : n.attributes.ruta,
        		iconCls  : n.attributes.iconCls,
        		cfg      : Ext.decode(n.attributes.cfg),
        		dsc      : n.attributes.dsc,
        		f1       : n.attributes.f1,
        		panel    : n.attributes.panel || 'Panel',
        		closable : true
        	};
        	
//        	window.open("http://rimachome");
        	if (w.id == 6749){
        		window.open(w.url);
        	}else{
        		this.addTab(w);
        	}

        	/* INI WQ */
        	
        	var r = '', opcc = '';
    		for (var x = 2; x<n.getPath().split('/').length;x++)
    		{
    			opcc = n.getPath().split('/')[x];
    			r = r + '/' + treeView.getNodeById(n.getPath().split('/')[x]).text;

    		};
    		
    		var codaplicacion= '';
    		var dscaplicacion = n.attributes.mod;
    		var codopcion = opcc;
    		var dscopcion = r;
    		var p = {codaplicacion:codaplicacion,dscaplicacion:dscaplicacion,codopcion:codopcion,dscopcion:dscopcion};
            
     		var par = Ext.apply(p);

     		callServer(rootSAS + 'sas.do?method=regUsuarioxOpcion',par, function(v, j){
     			
     			},this, 
     			function(msg, j){
     				Ext.Msg.error(msg);
     			} 
    		); 
    		
    		/* FIN WQ */
        	
        },
		//[FIN - EHUAMANI - 08/04/2015]
        addTab: function(w){ 
        	var inspanel = {};       

            JS.include('/' + w.mod + '' + w.url, function(){
            	if (!this.getPanel(w.id)){
                	inspanel = new Ext.Panel({
                        id          : w.id,
                        title       : w.titulo,
        		        loadScripts : true,
        		        border      : false,
                        closable    : w.closable||true,
                        //iconCls     : w.iconCls,
                        cfg         : w.cfg,
                        dsc         : w.dsc,
        		        layout      : 'fit',
                        items : [Ext.decode(w.ns)({
                        			panel  : w.panel,
                        			border : false,
                        	        header : false,
                        	        cfg    : w.cfg
                                })],
                        listeners   : {
                        	'show': {
                        		fn: function(){
                        			if(inspanel.items.items[0].show) inspanel.items.items[0].show({});
                        		}, scope: this
                        	},
                        	'activate': {
                        		fn: function(){
                        			if(inspanel.items.items[0].activate) inspanel.items.items[0].activate({});
                        		}, scope: this
                        	},
                        	'beforedestroy': {
                        		fn: function(){
                                	for (var x=0; x<this.paneles.length; x++){
                                        if (this.paneles[x][0] == w.id){
                                        	delete this.paneles[x]; 
                                        	this.paneles.splice(x,1);
                                        	break;
                                        }
                                	}
                        			if(inspanel.items.items[0].onDestroy) inspanel.items.items[0].onDestroy({});
                        		}, scope: this
                        	}
                        }
                    });
                	this.setPanel(w.id, inspanel);
                	
            	}else{
            		inspanel = this.getPanel(w.id);
            	}
            	
                this.tabpanel.add(inspanel).show();
            }, this);
            
        },
        
        setPanel : function(id, panel){
        	this.paneles[this.paneles.length]=[id, panel];
        },
        
        getPanel : function(id){
        	var inspanel = null;
        	for (var x=0; x<this.paneles.length; x++){
                if (this.paneles[x][0] == id){
                	inspanel = this.paneles[x][1];
                	break;
                }
        	}
        	return inspanel;
        },
        
      //INI - DSM 14/01/2015
        validarOportunidad : function () {
			var ideoportunidadext = null;
			var param;
			urlAttrSession = 'sas.do?method=obtenerVariableSession&attr=IDE_OPORTUNIDAD';
			ideoportunidadext = httpGet(urlAttrSession);
			if(ideoportunidadext!="null"){			
				var P = Ext.apply({ideoportunidadext : ideoportunidadext ,consulta : 'C'});				
				callServer(rootINTSAPCRM+'oportunidad.do?method=actualizarOportunidad',P , function(v1, j){
		    		if(j.origen == 'success'){
						v1 = Ext.decode(v1);
						 if(v1.ideoportunidad){
							 
							P2 = Ext.apply({ideoportunidad : v1.ideoportunidad});							
							callServer(rootINTSAPCRM+'oportunidad.do?method=obtenerRolCanal',P2 , function(v2, j2){
								if(j2.origen == 'success'){								
									v2 = Ext.decode(v2);
									if (v2.numerocanales > 0){
										param = Ext.apply(v1,P,v2);
										param.indAdministrador = false;			 
										var titulo = "Oportunidad: "+param.ideoportunidadext + " - " + param.descripcion;
										var w={
												id       : Ext.id(),
												ns       : 'intsapcrm.oportunidad.detalleoportunidad',
												titulo   : titulo,
												mod      : 'INTSAPCRM',
												url      : '/oportunidad/detalleOportunidad.js',
												ruta     : '/oportunidad/detalleOportunidad.js',
												iconCls  : null,
												cfg      : param,
												dsc      : '',
												panel    : 'Panel',
												closable : true
										};									 
										this.addTab(w);	
									}else{
										Ext.Msg.alert('Aviso', 'El usuario ingresado seleccionado no tiene autorización para ver la oportunidad.');
									}

								}    	    		
							},this, function(msg, j){
								Ext.Msg.error(msg);
							});	
							
						 }else{
							 Ext.Msg.alert('Aviso', 'No se pudo obtener el detalle de la Oportunidad seleccionada.');
						 }
					}    	    		
				},this, function(msg, j){
				    	Ext.Msg.error(msg);
				});					
			}				
		}
        //FIN - DSM 14/01/2015
        
    }, config));
};
