# SortPropertyFile

As you all know property files contain key-value paired data, small files can be easily maintained & modified or a poprerty can be searched easily. Suppose a large porperty file you want to maintain and want a separation between each new property starts with nee alphabet then making changes to an small file is easy but ehrn it comes to large file it is not that much feasible to do.

It's not compulsory to sort or arrange proper order as it doesn't affect the system/application which uses it, it's just personal choice to make modification & searching easy through out a large file.

_So, I made this utility jar which does the sorting plus seperates new properties starts with different letter._

### Example:
  Let's assume we have a property file like this:: `data.properties`
  `username=xyz
   password=837363
   userType=admin
   etc=etc`
   
  After sorting you'll get something like this..
  `#######
   ## E ##
   #######
   
   etc=etc
   
   #######
   ## P ##
   #######
   
   password=837363
   
   #######
   ## U ##
   #######
   
   username=xyz
   userType=admin`

### Running the Jar:
  Just pass the file name as parameter to the jar as shown below
  _cmd/shell_ : `java -jar <jar-name>.jar <property-file-name>.properties` OR `<jar-name>.jar <property-file-name>.properties`
  
  ###### Note: 
     It will create a backup as well like `file.properties` will be backed up as `file-old.properties`
     Also while comparing two properties it ignore cases.(else all Upper case named property will be appeared first)
