${moduleName}
==========================

Description
-----------
This is a very basic module which can be used as a starting point in creating a new module.

Building from Source
--------------------
You will need to have Java 1.6+ and Maven 2.x+ installed.  Use the command 'mvn package' to 
compile and package the module.  The .omod file will be in the omod/target folder.

Alternatively you can add the snippet provided in the [Creating Modules](https://wiki.openmrs.org/x/cAEr) page to your 
omod/pom.xml and use the mvn command:

    mvn package -P deploy-web -D deploy.path="../../openmrs-1.8.x/webapp/src/main/webapp"

It will allow you to deploy any changes to your web 
resources such as jsp or js files without re-installing the module. The deploy path says 
where OpenMRS is deployed.

Installation
------------
1. Build the module to produce the .omod file.
2. Use the OpenMRS Administration > Manage Modules screen to upload and install the .omod file.

If uploads are not allowed from the web (changable via a runtime property), you can drop the omod
into the ~/.OpenMRS/modules folder.  (Where ~/.OpenMRS is assumed to be the Application 
Data Directory that the running openmrs is currently using.)  After putting the file in there 
simply restart OpenMRS/tomcat and the module will be loaded and started.

CAG Module endpoints
------------
1. CAG

`
POST http://localhost:8081/openmrs/ws/rest/v1/cag
`


`
{    
    "name": "Liphamolaa",
    "description": "Liphamola tsa Harabulane",
    "constituency": "Taung",
    "village": "Siloe",
    "disctrict": "Mafeteng"
    }
`

2. Updating a CAG:

`
POST http://localhost:8081/openmrs/ws/rest/v1/cag/{uuid}
`

`
{
    "name": "Liphamola",
    "description": "Liphamola tsa Harabulane",
    "constituency": "Taung",
    "village": "Siloe",
    "disctrict": "Mafeteng"
}
`

e.g you can execute the following methods

` GET http://localhost:8081/openmrs/ws/rest/v1/cag/{uuid}`

` DELETE http://localhost:8081/openmrs/ws/rest/v1/cag/{uuid}`

3. Updating members of a CAG (CAG Patient)

` POST http://localhost:8081/openmrs/ws/rest/v1/cagPatient` 

`
{
    "cagUuid": "67d30e4-3c38-11ee-be56-0242ac120002",
    "uuid": "24060928-89df-442b-8f43-a4ae138ada4c"
}
`

To remove a member from any cag where they are active:

`
DELETE http://localhost:8081/openmrs/ws/rest/v1/cagPatient/{UUID}`

4. Updating a CAG visit

To open CAG Visit:

` POST http://localhost:8081/openmrs/ws/rest/v1/cagVisit `


`{
    
    "cagUuid": "35156159-41d9-4573-b317-28ed17dff0db",
    "dateStarted": "2023-10-10T06:14:00Z",
    "attenderUuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d",
    "absentees": {
        "349d17c1-441a-4543-8596-e479116a3a33": "Went to Bloemfontein",
        "98df327f-c531-4841-bdcf-90be3f62f127": "Visited Quthing"
        },   
    "locationName": "Unknown Location"
    }
`

Close CAG Visit:

` POST http://localhost:8081/openmrs/ws/rest/v1/cagVisit/{uuid}`

`
{
    "dateStopped": "2023-10-12 03:38:23"
}
`