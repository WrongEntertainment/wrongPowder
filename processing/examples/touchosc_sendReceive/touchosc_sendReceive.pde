/**
 * touchosc_sendReceive
 * by wrongPowder Library.
 *
 * Desc.
 *
 * @author              Paul Vollmer
 * @modified            2011.11.22
 * @processing-version  2.0a1
 */


wngTouchOSC touchosc;


/**
 * setup
 */
void setup() {
  size(400, 400);
  frameRate(30);
  
  touchosc = new wngTouchOSC("127.0.0.1", 8000, 9000);
  
  
  touchosc.selectPage("/3");
  
  touchosc.setLED("/1/ledanysolo", 0.5);
  touchosc.setLabel("/1/time", 1);
  touchosc.setButton("/1/sel-rec", 1);
  touchosc.setXYpad("/page6/xy1", 0.5, 0.5);
  touchosc.setFader("/1/volume1", 0.5);
  touchosc.setRotary("/3/pan", 0.9);
  
  //tOsc.setMultiToggle("/3/insertbypass", 12, 1, 0);
  int[] mt = {0,1,0,1,0,1,0,1,0,1,0,1};
  touchosc.setMultiToggle("/3/insertbypass", 12, 1, mt);
  // TODO
  //int[][] mt = new int[12][1];
  //mt[][0] = {0,1,0,1,0,1,0,1,0,1,0,1};
  //touchosc.setMultiToggle("/3/insertbypass", mt);
  touchosc.setMultiFader("/4/gain", 6, 1);
  float[] mf = {0.9, 0.2, 0.3, 0.7, 0.5, 0.6};
  touchosc.setMultiFader("/4/gain", mf);
  
  touchosc.show("/3/pan");
  touchosc.hide("/3/pan");
  touchosc.setColor("/4/gain", "orange");
}


/**
 * draw
 */
void draw() {
  background(0);
}


/**
 * oscEvent
 */
void oscEvent(OscMessage message) {
  // Get the Accelerometer values.
  println("ACC X: " + touchosc.accX(message));
  println("ACC Y: " + touchosc.accY(message));
  println("ACC Z: " + touchosc.accZ(message));
  
  // Get a TouchOSC controller message.
  println(touchosc.getValue(message, "/3/pan"));
}

