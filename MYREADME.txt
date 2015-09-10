Purpose :
---------
	Demo SpringMVC + JPA (EclipseLink) to HanaDatabase on CloudFoundry

Summary :
---------
	Hana database on Cloudfoundry cannot be accessed directly.
	For this case, it is accessed through the HDI layer(on the cloud) using a database tunnel mechanism.
	
	1) Create the HDI service in Cloudfoundry
	   $ cf create-service hana hdi-shared my-hdi-container
	   
	
	2) Setting up the database tunnel using chisel
	
	<< Install >> git clone git@github.wdf.sap.corp:cloudfoundry/chisel.git    
	Make a copy of auth.json from auth.json.example and update user passwd
	Already done at 
	$ cd /c/sdrive/projects/sap_git/chisel/ 
	$ cat auth.json
	{ "i319984:domainpassword": [""] }
	
	<< Push to CF >> 
	This will be the proxy app on CF to the HanaDatabase(via my-hdi-container) 
	$ cf push i319984-chisel-app --no-start
	$ cf bind-service i319984-chisel-app my-hdi-container
	$ cf start i319984-chisel-app
	$ cf env i319984-chisel-app
	
	<< Get database connection info >>
	We will use this to setup the local JDBC recsource in the META-INF/context.xml later in Tomcat
	$ cf env i319984-chisel-app | egrep 'host|hdi_user|hdi_password|port'
     "hdi_password": "u5Z2D1mVulsTSPyQ8ESeX5qUS4dgCEcp",
     "hdi_user": "HDI_6060AA98DEC74244AE6B72F313DF8F11",
     "host": "10.78.148.144",
     "port": "30015",
     
     << Run the proxy client >>
     We are using the "host" and "port" info from previous step
     So, 10.78.148.144 is the host of the chisel app on CF 30015 is the port
     12345 is the port number you want to assign from the localhost proxy. 
     This will be used as the jdbc connection parameter in Tomcat resource. 
     You will need to set the HTTP_PROXY first ( just . .profile)
	$ bin\chisel_windows_amd64.exe client --auth i319984:domainpassword https://i319984-chisel-app.cfapps.neo.ondemand.com/ 12345:10.78.148.144:30015
 
 
 	3) Setup the JDBC on tomcat
 	   Basically Create ~/META-INF/context.xml
 	   Use the hdi_user/hdi_password and the localhost:12345 that we've talked about above.
 	   
 	4) Now all set for development, look at the pom.xml for the dependencies 
       