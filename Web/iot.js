setInterval(function(){





    //getTemperature
$( document ).ready(function() {

    
    var script = document.createElement('script');
    script.src = 'http://code.jquery.com/jquery-1.6.2.min.js';
    document.getElementsByTagName('head')[0].appendChild(script); 


      var data = {};
      data.title = "title";
      data.message = "message";

      $.ajax({
        type: 'GET',
        data: JSON.stringify(data),
            contentType: 'application/json',
                    url: 'http://172.16.220.137:4300/getById?id=18',            
                    success: function(data) {
                        console.log('success');
                        document.getElementById('temp').value =12;
                        //document.getElementById('temp').value = JSON.stringify(data); 
                        //JSON.stringify(data);
                        console.log(data);

                        //alert(JSON.stringify(data));
                    }
                }); 
     
    });
     //getSeuil
$( document ).ready(function() {
      var data = {};
      data.title = "title";
      data.message = "message";

      $.ajax({
        type: 'GET',
        data: JSON.stringify(data),
            contentType: 'application/json',
                    url: 'http://172.16.220.137:4300/getSeuil',            
                    success: function(data) {
                        console.log('success');
                        document.getElementById('tempt').value = JSON.stringify(data); 
                        //JSON.stringify(data);
                        console.log(data);

                        //alert(JSON.stringify(data));
                    }
                }); 
     
    });

    //getLuminosity
   
  $( document ).ready(function() {
      var data = {};
      data.title = "title";
      data.message = "message";

      $.ajax({
        type: 'GET',
        data: JSON.stringify(data),
            contentType: 'application/json',
                    url: 'http://172.16.220.137:4300/getlum',            
                    success: function(data) {
                        console.log('success');
                        document.getElementById('lum').value = "dddddd"; 
                        //JSON.stringify(data);
                        console.log(data);

                        //alert(JSON.stringify(data));
                    }
                }); 
     
    });

//getSound

      $( document ).ready(function() {
      var data = {};
      data.title = "title";
      data.message = "message";

      $.ajax({
        type: 'GET',
        data: JSON.stringify(data),
            contentType: 'application/json',
                    url: 'http://172.16.220.137:4300/getsound',            
                    success: function(data) {
                        console.log('success');
                        document.getElementById('sound').value = JSON.stringify(data); 
                        //JSON.stringify(data);
                        console.log(data);

                        //alert(JSON.stringify(data));
                    }
                }); 
     
    });

  },3000);



  $( document ).ready(function() {

          $('#temp').click(function(){
            var temp = document.getElementById('temp').value ;
            if(document.getElementById('tempt').value > temp ){
                      $('#tempb').css('background-color','red');

            }
    });
          });
  function myFunction() {

   //alert("nizar");
            var data = {};
      data.title = "title";
      data.message = "message";

      $.ajax({
        type: 'GET',
        data: JSON.stringify(data),
            contentType: 'application/json',
                    url: 'http://172.16.220.137:4300/getetat',            
                    success: function(data) {
                        console.log('success');
                        alert(JSON.stringify(data));
                    }
                }); 
  }
