/**
 *  wrongPowder is developed by wrong-entertainment & powder
 *
 *
 *  Copyright 2011 Paul Vollmer & Tim Pulver
 *  paulvollmer.net
 *  vollmerpaul@yahoo.de
 *  timpulver.de
 *  pulver.tim@googlemail.com
 * 
 *  This file is part of wrongPowder library.
 *
 *  wrongPowder is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with wrongPowder. If not, see <http://www.gnu.org/licenses/>.
 * 
 *  @author		##author##
 *  @modified	##date##
 *  @version	##version##
 */

package wrongPowder.math;

import processing.core.PApplet;
import processing.core.PVector;


/**
 * Math
 * 
 * @example math_basic 
 */
public class Geometry {
	

	/**
	 * A Constructor, usually called in the setup() method in your sketch to<br>
	 * initialize and start the library.
	 * 
	 * @example math_basic
	 */
	public Geometry() {}
	
	
	/**
	 * A cycloid is the curve traced by a point on the rim of circular wheel as the wheel rolls<br>
	 * along a straight line. It is an example of a roulette, a curve generated by a curve rolling<br>
	 * on another curve.<br>
	 * The cycloid is the solution to the brachistochrone problem (i.e. it is the curve of fastest<br>
	 * descent under gravity) and the related tautochrone problem (i.e. the period of an object in<br>
	 * descent without friction inside this curve does not depend on the ball's starting position).<p>
	 * 
	 * <img src="http://upload.wikimedia.org/wikipedia/commons/6/69/Cycloid_f.gif"
	 *      alt="wikipedia source"></p><p>
	 * 
	 * More information at <a href="http://en.wikipedia.org/wiki/Cycloid">wikipedia.<p>
	 *
	 * @param radius The Radius.
	 * @param time Time.
	 */
	public PVector cycloid(float radius, float time) {
	  PVector v = new PVector();
	  v.x = radius * (time - PApplet.sin(time));
	  v.y = radius * (1 - PApplet.cos(time));
	  return v;
	}


	/**
	 * In geometry, an epicycloid is a plane curve produced by tracing the path of a chosen point<br>
	 * of a circle � called an epicycle � which rolls without slipping around a fixed circle.<br>
	 * It is a particular kind of roulette.<p>
	 * 
	 * <img src="http://upload.wikimedia.org/wikipedia/commons/a/ae/EpitrochoidOn3-generation.gif"
	 *      alt="wikipedia source"><br>
	 * The red curve is an epicycloid traced as the small circle (radius r = 1) rolls<br>
	 * around the outside of the large circle (radius R = 3).</p><p>
	 * 
	 * More information at <a href="http://en.wikipedia.org/wiki/Epicycloid">wikipedia</a> or
	 * <a href="http://mathworld.wolfram.com/Epicycloid.html">mathworld.wolfram.</a><p>
	 *
	 * @param radius The radius.
	 * @param roll Rolling around the outside of a fixed circle of radius.
	 * @param time Time.
	 */
	public PVector epicycloid(float radius, float roll, float time) {
	  PVector v = new PVector();
	  v.x = (radius+roll) * PApplet.cos(time) - roll * PApplet.cos( ((radius+roll)/roll) * time);
	  v.y = (radius+roll) * PApplet.sin(time) - roll * PApplet.sin( ((radius+roll)/roll) * time);
	  return v;
	}


	/**
	 * An epitrochoid is a roulette traced by a point attached to a circle of radius r rolling<br>
	 * around the outside of a fixed circle of radius R, where the point is a distance d from<br>
	 * the center of the exterior circle.<p>
	 * 
	 * <img src="http://upload.wikimedia.org/wikipedia/commons/2/20/EpitrochoidIn3.gif"
	 *      alt="wikipedia source"></p>
	 * The epitrochoid with R = 3, r = 1 and d = 1/2.<p><p>
	 * 
	 * More information at <a href="http://en.wikipedia.org/wiki/Epitrochoid">wikipedia</a> or
	 * <a href="http://mathworld.wolfram.com/Epitrochoid.html">mathworld.wolfram.</a><p>
	 *
	 * @param radius The radius.
	 * @param roll Rolling around the outside of a fixed circle of radius.
	 * @param dist Distance from the center of the exterior circle.
	 * @param time Time.
	 */
	public PVector epitrochoid(float radius, float roll, float dist, float time) {
	  PVector v = new PVector();
	  v.x = (radius+roll) * PApplet.cos(time) - dist * PApplet.cos( ((radius+roll)/roll) * time);
	  v.y = (radius+roll) * PApplet.sin(time) - dist * PApplet.sin( ((radius+roll)/roll) * time);
	  return v;
	}


	/**
	 * In geometry, a hypocycloid is a special plane curve generated by the trace of a fixed point<br>
	 * on a small circle that rolls within a larger circle. It is comparable to the cycloid but<br>
	 * instead of the circle rolling along a line, it rolls within a circle.<p>
	 * 
	 * <img src="http://upload.wikimedia.org/wikipedia/commons/b/b5/Deltoid2.gif"
	 *      alt="wikipedia source"></p>
	 * The red curve is a hypocycloid traced as the smaller black circle rolls around inside the<br>
	 * large blue circle (parameters are R=3.0, r=1.0, and so k=3, giving a deltoid).<p><p>
	 * 
	 * More information at <a href="http://en.wikipedia.org/wiki/Hypocycloid">wikipedia</a> or
	 * <a href="http://mathworld.wolfram.com/Hypocycloid.html">mathworld.wolfram.</a><p>
	 *
	 * @param radius The radius.
	 * @param numCorners Number of corners.
	 * @param time Time.
	 */
	public PVector hypocycloid(float radius, float numCorners, float time) {
	  PVector v = new PVector();
	  v.x = (radius/numCorners) * ( (numCorners-1) * PApplet.cos(time) - PApplet.cos( (numCorners-1) * time) );
	  v.y = (radius/numCorners) * ( (numCorners-1) * PApplet.sin(time) + PApplet.sin( (numCorners-1) * time) );
	  return v;
	}


	/**
	 * A hypotrochoid is a roulette traced by a point attached to a circle of radius r rolling<br>
	 * around the inside of a fixed circle of radius R, where the point is a distance d from<br>
	 * the center of the interior circle.<p>
	 * 
	 * <img src="http://upload.wikimedia.org/wikipedia/commons/f/fa/HypotrochoidOutThreeFifths.gif"
	 *      alt="wikipedia source"></p>
	 * The red curve is a hypotrochoid drawn as the smaller black circle rolls around inside the<br>
	 * larger blue circle (parameters are R = 5, r = 3, d = 5).<p><p>
	 * 
	 * More information at <a href="http://en.wikipedia.org/wiki/Hypotrochoid">wikipedia</a> or
	 * <a href="http://mathworld.wolfram.com/Hypotrochoid.html">mathworld.wolfram.</a><p>
	 *
	 * @param radius The radius.
	 * @param roll Rolling around the inside of a fixed circle of radius.
	 * @param dist Distance from the center of the interior circle.
	 * @param time Time.
	 */
	public PVector hypotrochoid(float radius, float roll, float dist, float time) {
		PVector v = new PVector();
		v.x = (radius-roll) * PApplet.cos(time) + dist * PApplet.cos( ((radius-roll)/roll) * time);
		v.y = (radius-roll) * PApplet.sin(time) - dist * PApplet.sin( ((radius-roll)/roll) * time);
		return v;
	}
	
	/**
	 * A harmonograph is a mechanical apparatus that employs pendulums to create a geometric image.<br>
	 * The drawings created typically are Lissajous curves, or related drawings of greater complexity.<br>
	 * The devices, which began to appear in the mid-19th century and peaked in popularity in the 1890s,<br>
	 * cannot be conclusively attributed to a single person, although Hugh Blackburn, a professor of<br>
	 * mathematics at the University of Glasgow, is commonly believed to be the official inventor.<p>
	 * 
	 * <img src="http://upload.wikimedia.org/wikipedia/en/a/a9/Lateral2.jpg"
	 *      alt="wikipedia source"></p>
	 * 
	 * More information at <a href="http://en.wikipedia.org/wiki/Harmonograph">wikipedia.<p>
	 * 
	 * @param A1
	 * @param A2
	 * @param A3
	 * @param A4
	 * @param f1
	 * @param f2
	 * @param f3
	 * @param f4
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param t time
	 */
	public PVector harmonograph(float A1, float A2, float A3, float A4, 
								float f1, float f2, float f3, float f4, 
								float p1, float p2, float p3, float p4,
								float d1, float d2, float d3, float d4, float t) {
		
		PVector v = new PVector();
		v.x = A1 * PApplet.sin(f1 * t + PApplet.PI * p1) * (float)Math.exp(-d1 * t) + A2 * PApplet.sin(f2 * t + PApplet.PI * p2) * (float)Math.exp(-d2 * t);
		v.y = A3 * PApplet.sin(f3 * t + PApplet.PI * p3) * (float)Math.exp(-d3 * t) + A4 * PApplet.sin(f4 * t + PApplet.PI * p4) * (float)Math.exp(-d4 * t);
		return v;
	}
	
}