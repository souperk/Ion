Ion
===
Ion is an example Web(HTTP) Server developed in java by Kostas "souperk" Alexopoulos (kostas@alcinia.net).

It uses Apache Commons Configuration, Apache Commons IO, Apache Commons Lang(2.6), Apache Commons Logging, Apache log4j, Apache commons Collections libraries. 

Current release is 1.3.0 .

Feutures
===

Currently Ion functions both as an http server and as a proxy to another http server.

As an http server Ion doesn't support any known web language like php and javascript thought thankfully browsers recognise on their own the html files.

Name
===
Ionians were one of the 3 major greek tribes of ancient period. While the name ion refers to Ion-ians.

Future release plan
===
 1. Compatibility with more (if not all) http headers.

   Current ion only uses the Host http header. However on the next releases it should become compatible with various other http headers.
   
 2. Stable releases.

   Current releases are unstable and things change from day to day(mostly due to my inexperiece). After 2.0.0 Ion should become more stable keeping backward compability with at least same major releases (example 2.2 should be compatible with 2.4). 
