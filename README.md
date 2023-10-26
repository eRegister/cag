# CAG


Description
-----------
The CAG module is [ICAP Lesotho's ](https://icap.columbia.edu/where-we-work/lesotho/) effort, working with [NUL](https://www.nul.ls/technology/) to extend eRegister to have better support for [Community ART Groups](https://www.pepfarsolutions.org/solutions/tag/community+ART+groups+%28CAGs%29)

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
#### 1. CAG

`
POST http://localhost:8081/openmrs/ws/rest/v1/cag
`


```
{    
    "name": "Liphamolaa",
    "description": "Liphamola tsa Harabulane",
    "constituency": "Taung",
    "village": "Siloe",
    "disctrict": "Mafeteng"
    }
```

#### 2. Updating a CAG:

`
POST http://localhost:8081/openmrs/ws/rest/v1/cag/{uuid}
`

```
{
    "name": "Liphamola",
    "description": "Liphamola tsa Harabulane",
    "constituency": "Taung",
    "village": "Siloe",
    "disctrict": "Mafeteng"
}
```

e.g you can execute the following methods

` GET http://localhost:8081/openmrs/ws/rest/v1/cag/{uuid}`

` DELETE http://localhost:8081/openmrs/ws/rest/v1/cag/{uuid}`

#### 3. Updating members of a CAG (CAG Patient)

` POST http://localhost:8081/openmrs/ws/rest/v1/cagPatient` 

```
{
    "cagUuid": "67d30e4-3c38-11ee-be56-0242ac120002",
    "uuid": "24060928-89df-442b-8f43-a4ae138ada4c"
}
```

To remove a member from any cag where they are active:

`
DELETE http://localhost:8081/openmrs/ws/rest/v1/cagPatient/{UUID}`

#### 4. Updating a CAG visit

To open CAG Visit:

` POST http://localhost:8081/openmrs/ws/rest/v1/cagVisit `


```
{
    "cag": {
        "uuid": "35156159-41d9-4573-b317-28ed17dff0db"
    },
<<<<<<< HEAD
    "dateStarted": "2023-10-24 09:32:46",
    "attenderVisit": {
        "patient": {
            "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
    "dateStarted": "2023-10-12 03:32:46",
    "attenderVisit": {
        "patient": {
            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
        },
        "location": {
            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
        },
        "encounters": [
            {
                "encounterType": "81888515-3f10-11e4-adec-0800271c1b75",
                "encounterDatetime": "2023-10-12 03:32:46",
                "patient": {
<<<<<<< HEAD
                    "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
                },
                "location": {
                    "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                }, 
                "obs":[
                    {
                        "concept": {
                            "uuid": "9b1fa8e6-8209-4fcd-abd2-142887fc83e0"
                        },
                        "valueCoded": "a3e3fdfe-e03c-401d-a3fd-1c2553fefe53",
                        "valueCodedName": "HTC, Patient",
                        "obsDatetime": "2023-10-15 02:40:08",
                        "person": {
<<<<<<< HEAD
                            "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
                            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
                        },
                        "location":{
                            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                        }
                    },
                    {
                        "concept": {
                            "uuid": "5090AAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                        },
                        "valueNumeric": 140,
                        "obsDatetime": "2023-10-15 02:40:08",
                        "person": {
<<<<<<< HEAD
                            "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
                            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
                        },
                        "location":{
                            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                        }
                    },
                    {
                        "concept": {
                            "uuid": "5089AAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                        },
                        "valueNumeric": 60,
                        "obsDatetime": "2023-10-15 02:40:08",
                        "person": {
<<<<<<< HEAD
                            "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
                            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
                        },
                        "location":{
                            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                        }
                    },
                    {
                        "concept": {
                            "uuid": "4a2cec08-4512-4635-b1de-b3b698f56346"
                        },
                        "valueCoded": "562fee67-96c5-4b80-ba02-ba8805a28693",
                        "valueCodedName": "No signs",
                        "obsDatetime": "2023-10-15 02:40:08",
                        "person": {
<<<<<<< HEAD
                            "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
                            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
                        },
                        "location":{
                            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                        }
                    },
                    {
                        "concept": {
                            "uuid": "36e9c8b-3f10-11e4-adec-0800271c1b75"
                        },
                        "valueNumeric": 120,
                        "obsDatetime": "2023-10-15 02:40:08",
                        "person": {
<<<<<<< HEAD
                            "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
                            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
                        },
                        "location":{
                            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                        }
                    },
                    {
                        "concept": {
                            "uuid": "c379aa1d-3f10-11e4-adec-0800271c1b75"
                        },
                        "valueNumeric": 73,
                        "obsDatetime": "2023-10-15 02:40:08",
                        "person": {
<<<<<<< HEAD
                            "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
                            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
                        },
                        "location":{
                            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                        }
                    },
                    {
                        "concept": {
                            "uuid": "90f53912-95d5-4b5c-a9eb-81f3f937225e"
                        },
                        "valueNumeric": 24,
                        "obsDatetime": "2023-10-15 02:40:08",
                        "person": {
<<<<<<< HEAD
                            "uuid": "c8e31d37-c5d6-4b5c-9a59-5a3df1c04198"
=======
                            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
                        },
                        "location":{
                            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                        }
                    }
                ]
            }
        ]
    },
    "absentees": {
<<<<<<< HEAD
        "0934ab1b-07ca-4ebe-bd34-c7afbb89d3aa": "Went to Bloemfontein"
    },
    "locationName": "MCH"
=======
        "349d17c1-441a-4543-8596-e479116a3a33": "Went to Bloemfontein",
        "98df327f-c531-4841-bdcf-90be3f62f127": "Visited Quthing"
    },
    "locationName": "Unknown Location"
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
}
```

Close CAG Visit:

` POST http://localhost:8081/openmrs/ws/rest/v1/cagVisit/{uuid}`

```
{
    "dateStopped": "2023-10-12 03:38:23"
}
```

`add cag_visit table:`

```
CREATE TABLE `cag_visit` (
  `cag_visit_id` int(11) NOT NULL AUTO_INCREMENT,
  `cag_id` int(11) DEFAULT NULL,
  `uuid` varchar(38) NOT NULL,
  `next_encounter_date` date DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_changed` datetime DEFAULT NULL,
  `changed_by` int(11) DEFAULT NULL,
  `voided` tinyint(1) DEFAULT NULL,
  `voided_by` int(11) DEFAULT NULL,
  `date_voided` date DEFAULT NULL,
  `void_reason` varchar(255) DEFAULT NULL,
  `date_started` datetime DEFAULT NULL,
  `date_stopped` datetime DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `visit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cag_visit_id`),
  KEY `cag_id_fkey` (`cag_id`),
  KEY `patient_id` (`patient_id`),
  KEY `visit_id` (`visit_id`),
  CONSTRAINT `cag_id_fkey` FOREIGN KEY (`cag_id`) REFERENCES `cag` (`cag_id`),
  CONSTRAINT `cag_visit_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON UPDATE CASCADE,
  CONSTRAINT `cag_visit_ibfk_2` FOREIGN KEY (`visit_id`) REFERENCES `visit` (`visit_id`) ON UPDATE CASCADE
);
```
`add missed_drug_pickup table:`

```
CREATE TABLE missed_drug_pickup(
missed_drug_pickup_id int primary key auto_increment,
cag_visit_id int,
patient_id int,
reason_missed varchar(255),
foreign key (cag_visit_id) references cag_visit(cag_visit_id) on update cascade,
foreign key (patient_id) references patient(patient_id) on update cascade
);
```

### +++++++++++++++++++++CAG ENCOUNTER+++++++++++++++++++++

<<<<<<< HEAD
Create CAG Encounter (ART Follow-up(Encounter + Obs) + Prescription(drug order)):

`POST http://localhost:8081/openmrs/ws/rest/v1/cagEncounter/`

```
{
   "cag": {
       "uuid": "78a8700f-f03b-400d-957e-c00926a5b64c"
   },
   "cagVisit": {
       "uuid": "fb336040-b1aa-4ae3-9beb-4717fbb764fa"
   },
   "nextEncounterDate": "2013-11-18 20:00:04",
   "encounters": [
       {
           "encounterDatetime": "2023-10-18 20:18:08",
           "encounterType":{
               "uuid": "81852aee-3f10-11e4-adec-0800271c1b75"
           },
           "patient": {
               "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
           },
           "visit": {
               "uuid": "b1beec8b-f628-4f09-93c4-10c1a5e9fc84"
           },
           "location":{
               "uuid": "438a36ce-88cd-4f6f-9590-7ec4c76a1852"
           },
           "orders":[
               {
                   "type": "drugorder",
                   "patient": "3505b460-56ec-42f9-89d5-b45acaaadc2b",
                   "orderType": "131168f4-15f5-102d-96e4-000c29c2a5d7",
                   "concept": "9d155660-c16e-42d8-bff1-76cebe867e56",
                   "dateActivated" : "2023-10-18 20:18:08",
                   "autoExpireDate" : "2023-11-18 20:00:08",
                   "orderer" : "c1c26908-3f10-11e4-adec-0800271c1b75",
                   "urgency": "ON_SCHEDULED_DATE",
                   "careSetting": "6f0c9a92-6f24-11e3-af88-005056821db0",
                   "scheduledDate": "2023-10-18 20:18:08",
                   "dose": 1,
                   "doseUnits": "86239663-7b04-4563-b877-d7efc4fe6c46",
                   "frequency": "9d7c32a2-3f10-11e4-adec-0800271c1b75",
                   "quantity": 30.0,
                   "quantityUnits": "86239663-7b04-4563-b877-d7efc4fe6c46",
                   "drug": "189a5fc2-d29b-4ce5-b3ca-dc5405228bfc",
                   "numRefills": 0,
                   "duration": 30,
                   "durationUnits": "9d7437a9-3f10-11e4-adec-0800271c1b75",
                   "route": "9d6bc13f-3f10-11e4-adec-0800271c1b75",
                   "action": "NEW"
               }
           ],
           "obs":[
               {
                   "concept": {
                       "conceptId": 2403,
                       "uuid": "746818ac-65a0-4d74-9609-ddb2c330a31b"
                   },
                   "obsDatetime": "2023-10-18 20:18:08",
                   "person": "3505b460-56ec-42f9-89d5-b45acaaadc2b",
                   "location": "438a36ce-88cd-4f6f-9590-7ec4c76a1852",
                   "groupMembers": [
                       {
                           "concept": {
                               "conceptId": 3753,
                               "uuid": "65aa58be-3957-4c82-ad63-422637c8dd18"
                           },
                           "obsDatetime": "2023-10-18 20:18:08",
                           "person": {
                               "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                           },
                           "location":{
                               "uuid": "438a36ce-88cd-4f6f-9590-7ec4c76a1852"
                           },
                           "groupMembers": [
                               {
                                   "concept": {
                                       "conceptId": 3843,
                                       "uuid": "e0bc761d-ac3b-4033-92c7-476304b9c5e8"
                                   },
                                   "valueCoded": "cdabf848-7ad9-410d-9f0e-834bade07cce",
                                   "valueCodedName": "ART patient",
                                   "valueText": "ART patient",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   },
                                   "location":{
                                       "uuid": "438a36ce-88cd-4f6f-9590-7ec4c76a1852"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 3751,
                                       "uuid": "ed064424-0331-47f6-9532-77156f40a014"
                                   },
                                   "valueCoded": "a2065636-5326-40f5-aed6-0cc2cca81ccc",
                                   "valueCodedName": "Yes",
                                   "valueText": "Yes",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 3752,
                                       "uuid": "88489023-783b-4021-b7a9-05ca9877bf67"
                                   },
                                   "valueDatetime": "2013-11-18 20:00:04",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 2250,
                                       "uuid": "13382e01-9f18-488b-b2d2-58ab54c82d82"
                                   },
                                   "valueCoded": "225b0d93-d4b9-46b0-bbb2-1bce82c9107c",
                                   "valueCodedName": "1j=TDF-3TC-DTG",
                                   "valueDrug": "1j=TDF-3TC-DTG",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 4174,
                                       "uuid": "9eb00622-1078-4f7b-aa69-61e6c36db347"
                                   },
                                   "valueCoded": "e5e0461a-35c9-42f9-9a2b-7a66122d9d9d",
                                   "valueCodedName": "HIVTC, One month supply",
                                   "valueText": "1 month",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 3730,
                                       "uuid": "27d55083-5e66-4b5a-91d3-2c9a42cc9996"
                                   },
                                   "valueNumeric": 30,
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 2224,
                                       "uuid": "95e1fc28-84ab-4971-8bb1-d8ee68ef5739"
                                   },
                                   "valueCoded": "480042e0-3011-4652-b989-2e22b5a725f2",
                                   "valueCodedName": "Stage II",
                                   "valueText": "Stage II",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 3726,
                                       "uuid": "e8d05f4a-9c3f-4f99-941c-596f238f095f"
                                   },
                                   "valueCoded": "0621a9a2-7a26-4e93-8e38-3732d242ab28",
                                   "valueCodedName": "Good adherence",
                                   "valueText": "Good adherence",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 3728,
                                       "uuid": "3485a002-f72f-43fd-8ba7-0288273489da"
                                   },
                                   "valueNumeric": 30,
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "3505b460-56ec-42f9-89d5-b45acaaadc2b"
                                   }
                               }
                           ]
                       }
                   ]
               }           
           ]
       },
       {
           "encounterDatetime": "2023-10-18 20:18:08",
           "encounterType":{
               "uuid": "81852aee-3f10-11e4-adec-0800271c1b75"
           },
           "patient": {
               "uuid": "5208a219-bb2e-469d-8a18-4580492db046"
           },
           "visit": {
               "uuid": "486c7a06-076e-4345-845d-7a2d21b081ae"
           },
           "location":{
               "uuid": "438a36ce-88cd-4f6f-9590-7ec4c76a1852"
           },
           "orders":[
               {
                   "type": "drugorder",
                   "patient": "5208a219-bb2e-469d-8a18-4580492db046",
                   "orderType": "131168f4-15f5-102d-96e4-000c29c2a5d7",
                   "concept": "9d155660-c16e-42d8-bff1-76cebe867e56",
                   "dateActivated" : "2023-10-18 20:18:08",
                   "autoExpireDate" : "2023-11-18 20:00:08",
                   "orderer" : "c1c26908-3f10-11e4-adec-0800271c1b75",
                   "urgency": "ON_SCHEDULED_DATE",
                   "careSetting": "6f0c9a92-6f24-11e3-af88-005056821db0",
                   "scheduledDate": "2023-10-18 20:18:08",
                   "dose": 1,
                   "doseUnits": "86239663-7b04-4563-b877-d7efc4fe6c46",
                   "frequency": "9d7c32a2-3f10-11e4-adec-0800271c1b75",
                   "quantity": 30.0,
                   "quantityUnits": "86239663-7b04-4563-b877-d7efc4fe6c46",
                   "drug": "189a5fc2-d29b-4ce5-b3ca-dc5405228bfc",
                   "numRefills": 0,
                   "duration": 30,
                   "durationUnits": "9d7437a9-3f10-11e4-adec-0800271c1b75",
                   "route": "9d6bc13f-3f10-11e4-adec-0800271c1b75",
                   "action": "NEW"
               }
           ],
           "obs":[
               {
                   "concept": {
                       "conceptId": 2403,
                       "uuid": "746818ac-65a0-4d74-9609-ddb2c330a31b"
                   },
                   "obsDatetime": "2023-10-18 20:18:08",
                   "person": "5208a219-bb2e-469d-8a18-4580492db046",
                   "location": "438a36ce-88cd-4f6f-9590-7ec4c76a1852",
                   "groupMembers": [
                       {
                           "concept": {
                               "conceptId": 3753,
                               "uuid": "65aa58be-3957-4c82-ad63-422637c8dd18"
                           },
                           "obsDatetime": "2023-10-18 20:18:08",
                           "person": {
                               "uuid": "5208a219-bb2e-469d-8a18-4580492db046"
                           },
                           "location":{
                               "uuid": "438a36ce-88cd-4f6f-9590-7ec4c76a1852"
                           },
                           "groupMembers": [
                               {
                                   "concept": {
                                       "conceptId": 3843,
                                       "uuid": "e0bc761d-ac3b-4033-92c7-476304b9c5e8"
                                   },
                                   "valueCoded": "0f880c52-3ced-43ac-a79b-07a2740ae428",
                                   "valueCodedName": "ART patient",
                                   "valueText": "ART patient",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "5208a219-bb2e-469d-8a18-4580492db046"
                                   },
                                   "location":{
                                       "uuid": "438a36ce-88cd-4f6f-9590-7ec4c76a1852"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 3751,
                                       "uuid": "ed064424-0331-47f6-9532-77156f40a014"
                                   },
                                   "valueCoded": "a2065636-5326-40f5-aed6-0cc2cca81ccc",
                                   "valueCodedName": "Yes",
                                   "valueText": "Yes",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "5208a219-bb2e-469d-8a18-4580492db046"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 3752,
                                       "uuid": "88489023-783b-4021-b7a9-05ca9877bf67"
                                   },
                                   "valueDatetime": "2013-11-18 20:00:04",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "5208a219-bb2e-469d-8a18-4580492db046"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 2250,
                                       "uuid": "13382e01-9f18-488b-b2d2-58ab54c82d82"
                                   },
                                   "valueCoded": "225b0d93-d4b9-46b0-bbb2-1bce82c9107c",
                                   "valueCodedName": "1j=TDF-3TC-DTG",
                                   "valueDrug": "1j=TDF-3TC-DTG",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "5208a219-bb2e-469d-8a18-4580492db046"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 4174,
                                       "uuid": "9eb00622-1078-4f7b-aa69-61e6c36db347"
                                   },
                                   "valueCoded": "e5e0461a-35c9-42f9-9a2b-7a66122d9d9d",
                                   "valueCodedName": "HIVTC, One month supply",
                                   "valueText": "1 month",
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "5208a219-bb2e-469d-8a18-4580492db046"
                                   }
                               },
                               {
                                   "concept": {
                                       "conceptId": 3730,
                                       "uuid": "27d55083-5e66-4b5a-91d3-2c9a42cc9996"
                                   },
                                   "valueNumeric": 30,
                                   "obsDatetime": "2023-10-18 20:18:08",
                                   "person": {
                                       "uuid": "5208a219-bb2e-469d-8a18-4580492db046"
                                   }
                               }
                           ]
                       }
                   ]
               }           
           ]
       }
   ]   
}

```

=======
>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a

`Add cag_encounter table:`

```
CREATE TABLE `cag_encounter` (
  `cag_encounter_id` int(11) NOT NULL AUTO_INCREMENT,
  `cag_id` int(11) NOT NULL,
  `cag_visit_id` int(11) DEFAULT NULL,
  `encounter_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `cag_encounter_datetime` datetime DEFAULT NULL,
  `uuid` varchar(38) NOT NULL,
  `next_encounter_date` date DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_changed` datetime DEFAULT NULL,
  `changed_by` int(11) DEFAULT NULL,
  `voided` tinyint(1) DEFAULT '0',
  `voided_by` int(11) DEFAULT NULL,
  `date_voided` date DEFAULT NULL,
  `void_reason` varchar(255) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cag_encounter_id`),
  UNIQUE KEY `uuid` (`uuid`),
  KEY `cag_id` (`cag_id`),
  KEY `cag_visit_id` (`cag_visit_id`),
  KEY `encounter_id` (`encounter_id`),
  KEY `patient_id` (`patient_id`),
  KEY `changed_by` (`changed_by`),
  KEY `creator` (`creator`),
  KEY `voided_by` (`voided_by`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `cag_encounter_ibfk_1` FOREIGN KEY (`cag_id`) REFERENCES `cag` (`cag_id`) ON UPDATE CASCADE,
  CONSTRAINT `cag_encounter_ibfk_2` FOREIGN KEY (`cag_visit_id`) REFERENCES `cag_visit` (`cag_visit_id`) ON UPDATE CASCADE,
  CONSTRAINT `cag_encounter_ibfk_3` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`encounter_id`) ON UPDATE CASCADE,
  CONSTRAINT `cag_encounter_ibfk_4` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON UPDATE CASCADE,
  CONSTRAINT `cag_encounter_ibfk_5` FOREIGN KEY (`changed_by`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `cag_encounter_ibfk_6` FOREIGN KEY (`creator`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `cag_encounter_ibfk_7` FOREIGN KEY (`voided_by`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `cag_encounter_ibfk_8` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON UPDATE CASCADE
)
```

<<<<<<< HEAD
=======
Sample Test JSON Data (Represents ART Follow-up form(Encounter) + Prescription(drug order)):

`POST http://localhost:8081/openmrs/ws/rest/v1/cagVisit`

```
{
    "cag": {
        "uuid": "35156159-41d9-4573-b317-28ed17dff0db"
    },
    "cagVisit": {
        "uuid": "4c34a34e-79a3-4b83-a3f4-9e7a1802de17"
    },
    "nextEncounterDate": "2013-11-18 20:00:04",
    "encounter": {
        "encounterDatetime": "2023-10-18 20:18:08",
        "encounterType":{
            "uuid": "81852aee-3f10-11e4-adec-0800271c1b75"
        },
        "patient": {
            "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
        },
        "visit": {
            "uuid": "fa193e34-b8ad-4569-bc84-c737ea7073db"
        },
        "location":{
            "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
        },
        "orders":[
            {
                "type": "drugorder",
                "patient": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d",
                "orderType": "131168f4-15f5-102d-96e4-000c29c2a5d7",
                "concept": "9d155660-c16e-42d8-bff1-76cebe867e56",
                "dateActivated" : "2023-10-18 20:18:08",
                "autoExpireDate" : "2023-11-18 20:00:08",
                "orderer" : "c1c26908-3f10-11e4-adec-0800271c1b75",
                "urgency": "ON_SCHEDULED_DATE",
                "careSetting": "6f0c9a92-6f24-11e3-af88-005056821db0",
                "scheduledDate": "2023-10-18 20:18:08",
                "dose": 1,
                "doseUnits": "86239663-7b04-4563-b877-d7efc4fe6c46",
                "frequency": "9d7c32a2-3f10-11e4-adec-0800271c1b75",
                "quantity": 30.0,
                "quantityUnits": "86239663-7b04-4563-b877-d7efc4fe6c46",
                "drug": "189a5fc2-d29b-4ce5-b3ca-dc5405228bfc",
                "numRefills": 0,
                "dosingInstructions": "As directed",
                "duration": 30,
                "durationUnits": "9d7437a9-3f10-11e4-adec-0800271c1b75",
                "route": "9d6bc13f-3f10-11e4-adec-0800271c1b75",
                "action": "NEW"
            }
        ],
        "obs":[
            {
                "concept": {
                    "conceptId": 3843,
                    "uuid": "e0bc761d-ac3b-4033-92c7-476304b9c5e8"
                },
                "valueCoded": "0f880c52-3ced-43ac-a79b-07a2740ae428",
                "valueCodedName": "ART patient",
                "valueText": "ART patient",
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                },
                "location":{
                    "uuid": "8d6c993e-c2cc-11de-8d13-0010c6dffd0f"
                } 
            },
            {
                "concept": {
                    "conceptId": 3751,
                    "uuid": "ed064424-0331-47f6-9532-77156f40a014"
                },
                "valueCoded": "a2065636-5326-40f5-aed6-0cc2cca81ccc",
                "valueCodedName": "Yes",
                "valueText": "Yes",
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                }
            },
            {
                "concept": {
                    "conceptId": 3752,
                    "uuid": "88489023-783b-4021-b7a9-05ca9877bf67"
                },
                "valueDatetime": "2013-11-18 20:00:04",
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                }
            },
            {
                "concept": {
                    "conceptId": 2250,
                    "uuid": "13382e01-9f18-488b-b2d2-58ab54c82d82"
                },
                "valueCoded": "225b0d93-d4b9-46b0-bbb2-1bce82c9107c",
                "valueCodedName": "1j=TDF-3TC-DTG",
                "valueDrug": "1j=TDF-3TC-DTG",
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                }
            },
            {
                "concept": {
                    "conceptId": 4174,
                    "uuid": "9eb00622-1078-4f7b-aa69-61e6c36db347"
                },
                "valueCoded": "e5e0461a-35c9-42f9-9a2b-7a66122d9d9d",
                "valueCodedName": "HIVTC, One month supply",
                "valueText": "1 month",
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                }
            },
            {
                "concept": {
                    "conceptId": 3730,
                    "uuid": "27d55083-5e66-4b5a-91d3-2c9a42cc9996"
                },
                "valueNumeric": 30,
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                }
            },
            {
                "concept": {
                    "conceptId": 2224,
                    "uuid": "95e1fc28-84ab-4971-8bb1-d8ee68ef5739"
                },
                "valueCoded": "480042e0-3011-4652-b989-2e22b5a725f2",
                "valueCodedName": "Stage II",
                "valueText": "Stage II",
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                }
            },
            {
                "concept": {
                    "conceptId": 3726,
                    "uuid": "e8d05f4a-9c3f-4f99-941c-596f238f095f"
                },
                "valueCoded": "0621a9a2-7a26-4e93-8e38-3732d242ab28",
                "valueCodedName": "Good adherence",
                "valueText": "Good adherence",
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                }
            },
            {
                "concept": {
                    "conceptId": 3728,
                    "uuid": "3485a002-f72f-43fd-8ba7-0288273489da"
                },
                "valueNumeric": 30,
                "obsDatetime": "2023-10-18 20:18:08",
                "person": {
                    "uuid": "73ff4a1a-3cc8-4d90-966d-7ae764c6892d"
                }
            }
        ]
    }
}```

>>>>>>> 1889e1f64373608034fcb29d3a384f5d5240956a
### +++++++++++ Concepts Used (ART Followup Encounter) +++++++++++

Type of client -  3843
e0bc761d-ac3b-4033-92c7-476304b9c5e8
	
    Coded
	Treatment Buddy (3841)
	ART patient (3842)

Appointment scheduled - 3751
ed064424-0331-47f6-9532-77156f40a014
	
    Coded
	Yes (2146)
	No (2147)
	
	
ART, Follow-up date - 3752
88489023-783b-4021-b7a9-05ca9877bf67

ART Regimen - 2250
13382e01-9f18-488b-b2d2-58ab54c82d82
	Coded
	1c=AZT-3TC-NVP (2201)
	2c=AZT-3TC-LPV/r (2210)
	1j=TDF-3TC-DTG (4678)
	1k=ABC-3TC-DTG (4679)
	..e.t.c
	
ARV drugs supply duration - 4174
9eb00622-1078-4f7b-aa69-61e6c36db347
	
    Coded
	HIVTC, Two weeks supply (4243)
	HIVTC, One month supply (4175)
	HIVTC, Two months supply (4176)
	HIVTC, Three months supply (4177)
	HIVTC, Four months supply (4245)
	HIVTC, Five months supply (4246)
	HIVTC, Six months supply (4247)
	HIVTC, Seven+ months supply (4820)

HIVTC, HIV care WHO Staging - 2224
	95e1fc28-84ab-4971-8bb1-d8ee68ef5739
	
    Coded
	Stage I (2167)
	Stage II (2168)
	Stage III (2169)
	Stage IV (2170)
	
Cotrimoxazole adherence - 3726
e8d05f4a-9c3f-4f99-941c-596f238f095f
	
    Coded
	Good adherence (2305)
	Fair adherence (2306)
	Poor adherence (3702)
	Not Applicable (1975)
	
Cotrimoxazole days dispensed - 3728
3485a002-f72f-43fd-8ba7-0288273489da

AHD Client - 4958
c7b89a7a-af6e-4070-aa22-3b6cc35ad51f
	Coded
	Yes (2146)
	
### ========== Concepts Used (Drug Order) ==========

Drug(From DB)
	
    Members
	1c=AZT-3TC-NVP (2201)
	2c=AZT-3TC-LPV/r (2210)
	1j=TDF-3TC-DTG (4678) - 189a5fc2-d29b-4ce5-b3ca-dc5405228bfc
	1k=ABC-3TC-DTG (4679
	
Quantity - 5397
65015328-b2fb-4845-8d55-2df9391da698
	
Dose Quantity Units - 56
7a0c56d-3f10-11e4-adec-0800271c1b75
	
    coded
	Ayurvedic (332)
	Tablet(s) (342)
	Tablet(s) (342) - 86239663-7b04-4563-b877-d7efc4fe6c46
	Drops (338)
	Capsule(s) (357)
	Capsule(s) (357)
	Jelly (368)
	Lotion (345)
	ml (63)
	Ointment (344)
	mg (64)
	IU (947)
	Powder (343)
	Solution (340)
	Spray (341)
	Unit(s) (948)


Dosage Frequency - 4
818f75fe-3f10-11e4-adec-0800271c1b75
	
    Coded
	qD (5) - 8198989e-3f10-11e4-adec-0800271c1b75 (EVERY DAY==once a day)
	BID (6) (TWICE A DAY)
	TID (7)
	QID (8)

Drug Routes - 65
9d697972-3f10-11e4-adec-0800271c1b75
	
    Set Members
	Intramuscular (66)
	Intravenous (67)
	Oral (68)  - 9d6bc13f-3f10-11e4-adec-0800271c1b75
	Per Vaginal (69)
	Sub Cutaneous (70)
	Per Rectum (71)
	Sub Lingual (72)
	Nasogastric (74)
	Intradermal (963)
	Intraperitoneal (964)
	Intrathecal (965)
	Intraosseous (966)
	Topical (969)
	Nasal (970)
	Inhalation (971)
	
Date of Operation - 147 / Date - 996 (e605731b-2e81-41a9-8446-2ed442c339e2)
c38ce391-3f10-11e4-adec-0800271c1b75


Duration Units - 75
9d735b63-3f10-11e4-adec-0800271c1b75
	
    Members
	Day(s) (76) - 9d7437a9-3f10-11e4-adec-0800271c1b75
	Hour(s) (92)
	Week(s) (93)
	Month(s) (94)
	Minute(s) (976)
