define(["dojo/on", "client/companyLevel", "client/cars"], function(on, companyLevel, car){
    var app = {
        url: null,
        
        init :function(){
           // companyLevel.createGrid();
            companyLevel.init();
            car.init();
            
            
            
        },
        
        connect: function(url){
            this.url = url;
            if(url[url.length -1] != "/"){
                url = url+ "/";
            }
            
            companyLevel.connect(url+"companyLevels");
            car.companyLevelUrl =url+"companyLevels";   
            car.connect(url+"cars");
            companyLevel.activateToolBar();
            
            
            
            
            
        }
    }
    return app;
})
