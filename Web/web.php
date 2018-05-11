<html>
<head>
<meta charset="utf-8">
<Style>
.dot {
    height: 25px;
    width: 25px;
    border-radius: 50%;
    display: inline-block;
}
</style>
<script src="jquery.min.js"></script></head>
  <body onload="init()">
  <p>Status</p>
  <div><span class="dot" id="status" style="background-color: red"></span></div>
    <p>led</p>
      <input type="checkbox" name="flipswitch"  id="led" checked>
      <label for="led">
      </label>

    <p>Buzzer</p>
    
      <input type="checkbox" name="flipswitch"  id="fss" checked>
      <label  for="fss">
      </label>
      <p>Seuil temperature</p>
        <input id="StemperatureV" type="StemperatureV" placeholder="Seuil temperature"/>
        <button type="button" id="Stemperature">Send</button>
      <p>Seuil lumiere</p>
        <input id="SlumiereV" type="SlumiereV" placeholder="Seuil lumiere"/>
        <button type="button" id="Slumiere">Send</button>
      <p>Seuil sound</p>
        <input id="SsoundV" type="SsoundV" placeholder="Seuil sound"/>
        <button type="button" id="Ssound">Send</button>
        <br>
      <button type="button" id="refresh" > refresh</button>
      <p> temperature</p>
        <input id="temperature" type="temperature" placeholder=" temperature"/>
      <p> lumiere</p>
        <input id="lumiere" type="lumiere" placeholder=" lumiere"/>
      <p> sound</p>
        <input id="sound" type="sound" placeholder=" sound"/>
      <p> message</p>
        <input id="messageV" type="messageV" placeholder="message"/>
      <button type="button" id="message">submit</button>
  </div>
  
  <script type="text/javascript">
    var xmlhttp0;
    var en = false;
    setInterval(function(){

	    var url = "http://localhost:3000/getStatus";
	    xmlhttp0.open('GET', url, true);
	    xmlhttp0.send(null);   

	    xmlhttp0.onreadystatechange = function () {
	    	var status = document.getElementById("status");
	        var resultat = xmlhttp0.responseText;
	        if(resultat[0] == "0"){
	        	status.style.backgroundColor = "red";
	        	en = false;
	        } else {
	        	status.style.backgroundColor = "green";
	        	en = true;
	        }     
	    }

    }, 200);
	
	
	function init(){
	    xmlhttp0 = new XMLHttpRequest();
	    
		url = "http://localhost:3000/getStatus";
	    xmlhttp0.open('GET', url, true);
	    xmlhttp0.send(null);   

	    xmlhttp0.onreadystatechange = function () {
	    	var status = document.getElementById("status");
	        var resultat = xmlhttp0.responseText;
	        if(resultat[0] == "0"){
	        	status.style.backgroundColor = "red";
	        } else {
	        	status.style.backgroundColor = "green";
	        }     
	    }
		
	}
  
  
  document.getElementById("refresh").addEventListener("click", function(){
    $.get("http://localhost:3000/setEtatRefresh",function(data){
    })
    setTimeout(function(){$.get("http://localhost:3000/getVal",function(data){
        x=data.split(",");
        document.getElementById("temperature").value=x[1];
        document.getElementById("lumiere").value=x[0];
        document.getElementById("sound").value=x[2];
    }) }, 4000);
    /*/
      $.get("get.php",function(data){
        x=data.split(",");
        document.getElementById("temperature").value=x[1];
        document.getElementById("lumiere").value=x[0];
        document.getElementById("sound").value=x[2];
    })
      //*/
  });
  document.getElementById("led").addEventListener("click", function(){
      a=0;
      if(document.getElementById("led").checked){
        a=1;
      }
      $.get("http://localhost:3000/setEtatLed",function(data){
    })
  });
  document.getElementById("fss").addEventListener("click", function(){
      a=0;
      if(document.getElementById("fss").checked){
        a=1;
      }
      $.get("http://localhost:3000/setEtatBuzzeur",function(data){
    })
  });
  document.getElementById("Slumiere").addEventListener("click", function(){
      $.get("http://localhost:3000/setSeuilLum?lum="+document.getElementById("SlumiereV").value,function(data){
    })
  });
  document.getElementById("Stemperature").addEventListener("click", function(){
      $.get("http://localhost:3000/setSeuilTemp?temp="+document.getElementById("StemperatureV").value,function(data){
    })
  });
  document.getElementById("Ssound").addEventListener("click", function(){

      $.get("http://localhost:3000/setSeuilSound?sound="+document.getElementById("SsoundV").value,function(data){
    })
  });
  document.getElementById("message").addEventListener("click", function(){
      $.get("http://localhost:3000/setMessage?message="+document.getElementById("messageV").value,function(data){
    })
  });
  </script>
</body>
</html>