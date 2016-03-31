//this is a template
//https://www.cs.odu.edu/~zeil/cs350/s16/Protected/nameExtractionDesignNotes/instances.java.html



String[] outlookNames = {"sunny", "overcast", "rainy"};
Attribute outlook = new Attribute("outlook", fastV(outlookNames)); 
Attribute temperature = new Attribute("temperature"); // numeric
Attribute humidity = new Attribute("humidity"); // numeric
String[] windyNames = {"TRUE", "FALSE"};
Attribute windy = new Attribute("windy", fastV(windyNames)); 
String[] playNames = {"yes", "no"};
Attribute play = new Attribute("play", fastV(playNames)); 

FastVector attrInfo = new FastVector();
attrInfo.addElement(outlook);
attrInfo.addElement(temperature);
attrInfo.addElement(humidity);
attrInfo.addElement(windy);
attrInfo.addElement(play);

final int numberOfAttributes = attrInfo.size();

String[] trainingData = {
        "sunny,85,85,FALSE,no",
        "sunny,80,90,TRUE,no",
        "overcast,83,86,FALSE,yes",
        "rainy,70,96,FALSE,yes",
        "rainy,68,80,FALSE,yes",
        "rainy,65,70,TRUE,no",
        "overcast,64,65,TRUE,yes",
        "sunny,72,95,FALSE,no",
        "sunny,69,70,FALSE,yes",
        "rainy,75,80,FALSE,yes",
        "sunny,75,70,TRUE,yes",
        "overcast,72,90,TRUE,yes",
        "overcast,81,75,FALSE,yes",
        "rainy,71,91,TRUE,no"
};

Instances training = new Instances(
                "TrainingData", attrInfo, 
                trainingData.length);
training.setClass(play); // Which attribute holds the
                         // class/category that we want
                         // to predict?
for (String sdata: trainingData) {
        String[] values = sdata.split(",");
        Instance instance = new Instance(numberOfAttributes);
        instance.setValue(0, values[0]); // outlook
        instance.setValue(1, Double.parseDouble(values[1])); // temperature
        instance.setValue(2, Double.parseDouble(values[2])); // humidity
        instance.setValue(3, values[3]); // windy
        instance.setValue(4, values[4]); // play

        training.add(instance); // Add new instance to training data
}

⋮

//fastv is deprecated
  /**
   * Utility to build a FastVector from an array of Strings.
   * (This will be easier in later versions of WEKA where
   * FastVector will be a subclass of ArrayList.)  
   * @param data array of strings
   * @return a FastVector cotnaining those strings
   */
  private FastVector fastV(String[] data) {
      FastVector result = new FastVector(data.length);
      for (String s: data) {
          result.addElement(s);
      }
      return result;
  }