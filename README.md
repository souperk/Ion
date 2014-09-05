Ion
===
Ion is an example Web(HTTP)-Proxy Server developed in java by Kostas "souperk" Alexopoulos (kostas@alcinia.net).

It uses Apache Commons Configuration, Apache Commons IO, Apache Commons Lang(2.6), Apache Commons Logging, Apache log4j, Apache commons Collections libraries. Also it uses spring framework. 

Current release is 1.4.0 .

Feutures
===
As of 1.3.0 Ion is able to read http requests from browsers and transmit them to the corresponding http host(typically another proxy or http server). Also Ion if no http host is defined for the request it functions as an http server responding to the request on its own.

Future release plan
===
 1. Compatibility with more (if not all) http headers.

   Current ion only uses the Host http header. However on the next releases it should become compatible with various other http headers.
   
 2. Stable releases.

   Current releases are unstable and things change from day to day(mostly due to my inexperiece). After 2.0.0 Ion should become more stable keeping backward compability with at least the same major releases (example 2.2 should be compatible with 2.4). 

 3. More languages support.

   Ion currently just passes files as plain text to the browser. In the next releases a execution descriptor will be created in order to be able to execute code files (like php and java).

 4. Create more solid configuraiton that will last.

   Configuration in Ion currently is a little bit better than hardcoded. Configuration files locations should be more flexible and also configuration should be divided in better categories.
