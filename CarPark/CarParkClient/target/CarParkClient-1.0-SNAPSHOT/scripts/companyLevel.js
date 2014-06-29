define(
    [
    "dojo/_base/xhr",
    'dojox/grid/DataGrid' ,
    'dojo/data/ItemFileWriteStore' ,
    'dojo/dom',
    "dijit/registry",
    "dojo/query",
    'dojo/_base/json'  ,
    "dojo/dom-construct"
    
    ], 
    function(xhr, DataGrid, ItemFileWriteStore, dom, registry, query, json, construct){
        var companyLevel ={
            
            init: function(){
                var objRef = this;  
                registry.byId("clTab").onShow= function(){
               
                    objRef.activateToolBar();
                    objRef.connect(objRef.url);
                    
                };
            },
            
            activateToolBar : function(){
                var objRef = this;
                registry.byId("toolbar.create").onClick = function(){
                    objRef.openDialog();
                }
                
                registry.byId("toolbar.delete").onClick = function(){
                    objRef.deleteItems();
                }
            },
            connect: function(url, callback){
                if(!url){
                    return
                }
                this.url = url;
                var objRef = this;
                xhr.get({
                    url:url,
                    handleAs:"json",
                    load: function(data){
                        objRef.onLoad(data)
                        if(callback){
                            callback();
                        }
                    },
                    error: this.errorHandler
                });
            
            },
            
            onLoad: function(data){
                var companyLevelData = {
                    identifier: "id",
                    items: []
                };
                
                for(var i in data){
                    companyLevelData.items.push(data[i]);
                }
                
                var store = new ItemFileWriteStore({
                    data: companyLevelData
                });
                
                var layout = [[
                {
                    'name': 'id', 
                    'field': 'id'
                },
                {
                    'name': 'Name', 
                    'field': 'name'
                },

                {
                    'name': 'Level Value', 
                    'field': 'levelValue'
                }
                ]];
                var objRef= this;
                
              
                    this.grid = new DataGrid({
                        store: store,
                        structure: layout,
                        rowSelector: '20px',
                        style: "height:500px; width:750px;",
                        onRowDblClick : function(e){
                            var id = e.grid.getItem(e.rowIndex).id[0];
                            objRef.openDialog(id);
                        }
                    });
                    construct.empty("companyLevelsGrid");
                    this.grid.placeAt("companyLevelsGrid");
                    this.grid.startup();
                
                window.grid = this.grid;
                this.grid.setStore(store);
                
                
                
                
                
            },
            
            
            deleteItems :function(){
                var objRef= this;
                var selectedData = this.grid.selection.getSelected();
                for(var i=0; i< selectedData.length; i++){
                    xhr.del({
                        url: objRef.url + "/"+ selectedData[i].id[0] ,
                        handeAs:"json",                            
                        load : function(data){
                            objRef.connect(objRef.url);
                                
                        },
                        error: this.errorHandler
                    });
                }
            },
            
            openDialog: function(id){
                var objRef = this;
                
                if(id){
                    var dialog = registry.byId("updateCompanyLevelDialog");
                    var companyLevelName = registry.byId("companyLevelName");
                    var companyLevelValue = registry.byId("companyLevelValue");
                    var updateCompanyLevelButton = registry.byId("updateCompanyLevelButton");
                    updateCompanyLevelButton.onClick = function(){
                        xhr.put({
                            url: objRef.url + "/"+ id,
                            handeAs:"json",
                            putData : json.toJson({
                                id: id, 
                                name: companyLevelName.getValue(), 
                                levelValue: companyLevelValue.getValue()
                            }),
                            load : function(data){
                                objRef.connect(objRef.url, function(){
                                    dialog.hide();
                                })
                                
                            },
                            error: this.errorHandler
                        });
                    }
                    xhr.get({
                        url:this.url+"/"+id ,
                        handleAs:"json",
                        load: function(data){
                            companyLevelName.setValue(data.name);                           
                            companyLevelValue.setValue(data.levelValue);
                            dialog.show();
                        },
                        error: this.errorHandler
                    
                    });
                }
                else{
                    var dialog = registry.byId("newCompanyLevelDialog");
                    var companyLevelName = registry.byId("newCompanyLevelName");
                    var createCompanyLevelButton = registry.byId("addCompanyLevelButton");
                    companyLevelName.setValue("");
                    createCompanyLevelButton.onClick = function(){
                        xhr.post({
                            url: objRef.url,
                            handeAs:"json",
                            postData : json.toJson({
                                name: companyLevelName.getValue()
                            }),
                            load : function(data){
                                objRef.connect(objRef.url, function(){
                                    dialog.hide();
                                })
                                
                            },
                            error: this.errorHandler
                        });
                    }
                   
                    
                    dialog.show();
                }
                
            },
            errorHandler : function(e){
                if(e.xhr){
                    var xhr = e.xhr;
                    if(xhr.status == 404){
                        alert("Coud not connect to api");
                    }
                    else{
                        var jsonErr = json.fromJson(xhr.responseText);
                        if(jsonErr.causedBy){
                            alert(jsonErr.causedBy);
                        }
                        else{
                            alert(xhr.responseText);
                        }
                    }
                }
            }
        }
    
        return companyLevel;
    })

