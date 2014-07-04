define(
    [
    "dojo/_base/xhr",
    'dojox/grid/DataGrid' ,
    'dojo/data/ItemFileWriteStore' ,
    'dojo/dom',
    "dijit/registry",
    "dojo/query",
    'dojo/_base/json'  ,
    "dojo/dom-construct",
    "dojo/data/ObjectStore",
    "dojo/store/Memory"
    
    ], 
    function(xhr, DataGrid, ItemFileWriteStore, dom, registry, query, json, construct, ObjectStore, Memory){
        var cars ={
            
            init: function(){
                var objRef = this;  
                registry.byId("carTab").onShow= function(){               
                    objRef.activateToolBar();
                    xhr.get({
                        url: objRef.companyLevelUrl,
                        handleAs:"json",
                        load: function(clData){
                            var companyLevelData = [{
                                id: "none", 
                                label: "ALL CARS"
                            }];
                            var companyLevel = registry.byId("carsByCompanyLevel");
                            companyLevel.onChange= function(){
                                var url =  objRef.url;
                                var oldUrl = objRef.url;
                                if(companyLevel.getValue() && companyLevel.getValue() != "none"){
                                    url+="?companyLevel="+companyLevel.getValue();
                                }
                                objRef.connect(url);
                                objRef.url = oldUrl;
                            }
                            for(var i in clData){
                                companyLevelData.push({
                                    id: i, 
                                    label: clData[i].name
                                });
                            }
                            
                            var memory = new Memory({
                                data: companyLevelData
                            });
                
                            var store = ObjectStore({
                                objectStore: memory
                            });
                            
                            companyLevel.setStore(store);
                            var oldUrl = objRef.url;
                            var url =  objRef.url;
                            if(companyLevel.getValue() && companyLevel.getValue() != "none"){
                                url+="?companyLevel="+companyLevel.getValue();
                            }
                            objRef.connect(url);
                            objRef.url = oldUrl;
                        }
                    });
                    
                    
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
                    if(data[i].companyLevel && data[i].companyLevel.name)
                        data[i].companyLevel = data[i].companyLevel.name;
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
                    'name': 'Model', 
                    'field': 'model'
                },

                {
                    'name': 'SPZ', 
                    'field': 'spz'
                },
                {
                    'name': 'brand', 
                    'field': 'brand'
                },
                {
                    'name': 'Available', 
                    'field': 'available'
                },
                {
                    'name': 'Company Level', 
                    'field': 'companyLevel'
                },
                {
                    'name': 'CreationYear', 
                    'field': 'creationYear'
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
                construct.empty("carsGrid");
                this.grid.placeAt("carsGrid");
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
                var carDialog = registry.byId("carDialog");
                var model = registry.byId("model");
                var spz = registry.byId("spz");
                var brand = registry.byId("brand");
                var available= registry.byId("available");
                var creationYear = registry.byId("creationYear");
                var companyLevel = registry.byId("carCompanyLevel");
                var carButton = registry.byId("addCar");
               
                xhr.get({
                    url: this.companyLevelUrl,
                    handleAs:"json",
                    load: function(clData){
                        var os = new ObjectStore({
                                objectStore: store
                            });
                            var companyLevelData = [];
                                
                
                            for(var i in clData){
                                companyLevelData.push({
                                    id: i, 
                                    label: clData[i].name
                                });
                            }
                            
                            var memory = new Memory({
                                data: companyLevelData
                            });
                
                            var store = ObjectStore({
                                objectStore: memory
                            });
                            
                            companyLevel.setStore(store);
                        if(id){
                           

                            
                            carButton.onClick = function(){
                                xhr.put({
                                    url: objRef.url + "/"+ id,
                                    handeAs:"json",
                                    putData : json.toJson({
                                        id: id, 
                                        model: model.getValue(), 
                                        brand: brand.getValue(),
                                        available: available.getValue(), 
                                        creationYear: creationYear.getValue(),
                                        companyLevel: companyLevel.getValue(), 
                                        spz: spz.getValue()
                                
                                    }),
                                    load : function(data){
                                        objRef.connect(objRef.url, function(){
                                            carDialog.hide();
                                        })
                                
                                    },
                                    error: objRef.errorHandler
                                });
                            }
                    
                    
                            xhr.get({
                                url:objRef.url+"/"+id ,
                                handleAs:"json",
                                load: function(data){
                                    model.setValue(data.model);
                                    spz.setValue(data.spz);
                                    brand.setValue(data.brand);
                                    available.setValue(data.available);
                                    creationYear.setValue(data.creationYear);
                                    if(data.companyLevel)
                                        companyLevel.setValue(data.companyLevel.id);
                           
                                    carDialog.show();
                                },
                                error: this.errorHandler
                    
                            });
                        }
                        else{
                            model.setValue("");
                            brand.setValue("");
                            available.setValue(false);
                            creationYear.setValue("");
                           
                            spz.setValue("");
                            carButton.onClick = function(){
                                xhr.post({
                                    url: objRef.url,
                                    handeAs:"json",
                                    putData : json.toJson({
                                      
                                        model: model.getValue(), 
                                        brand: brand.getValue(),
                                        available: available.getValue(), 
                                        creationYear: creationYear.getValue(),
                                        companyLevel: companyLevel.getValue(), 
                                        spz: spz.getValue()
                                
                                    }),
                                    load : function(data){
                                        objRef.connect(objRef.url, function(){
                                            carDialog.hide();
                                        })
                                
                                    },
                                    error: objRef.errorHandler
                                });
                            }
                   
                    
                            carDialog.show();
                        }
                            
                    },
                    error: this.errorHandler
                });
                
                
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
    
        return cars;
    })

