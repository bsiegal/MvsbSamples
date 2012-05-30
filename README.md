[Spiffy UI](http://www.spiffyui.org) - GWT made simple
==================================================

This project includes different uses of Spiffy UI's Multi-valued Suggest Box: single-valued, multi-valued with defaults, advanced (Facebook-like) styling of suggestions, and suggestions retrieved client-side (without REST).

This project was created from the [Spiffy UI Framework](http://www.spiffyui.org) project creator, which builds a simple REST application with Apache Maven.

![Multi-valued Suggest Box screen shot](/spiffyui/MvsbSamples/raw/master/mvsb_screenshot.png)


Building and Running MvsbSamples
--------------------------------------

This project is built with [Apache Maven](http://maven.apache.org/).  
    
Go to your project's root directory and run the following command:

    mvn package jetty:run
        
This will download the required libraries, build your project, and run it.  You can access the running application here:

    http://localhost:8080
    

Debugging through Eclipse
--------------------------------------

See [Spiffy UI's GWT Dev Mode page](http://www.spiffyui.org/#!hostedMode) for more information.


License
--------------------------------------

Spiffy UI is available under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
