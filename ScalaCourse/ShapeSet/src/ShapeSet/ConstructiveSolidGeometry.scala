package ShapeSet

object ConstructiveSolidGeometry {
  // we represent 2D-sets by their characteristic function
  type Shape = ((Double, Double) => Boolean)         

  // define few basic primitives to work with
  def wholePlane: Shape = (_, _) => true
  def emptySet: Shape = (_, _) => false
  def upperHalfPlane: Shape = (_, y) => y >= 0
  def unitCircle: Shape = (x, y) => (x * x + y * y) <= 1

  // intersections and unions for two operands, complements
  def intersection(a: Shape, b: Shape): Shape = (x, y) => a(x, y) && b(x, y)
  def union(a: Shape, b: Shape): Shape = (x, y) => a(x, y) || b(x, y)
  def complement(a: Shape): Shape = !a(_, _)

  // scale, rotate, translate: unlike the general "map-by-whatever" in the assignment, these functions are easily invertible
  def scale(xScaling: Double, yScaling: Double)(a: Shape): Shape = (x, y) => a(x / xScaling, y / yScaling)
  def translate(xOffset: Double, yOffset: Double)(a: Shape): Shape = (x, y) => a(x - xOffset, y - yOffset)
  def rotate(angle: Double)(a: Shape): Shape = (x, y) => {val c = math.cos(angle); val s = math.sin(angle); a(c * x + s * y, -s * x + c * y)}

  // "arrays" in the CAD-sense
  def array(combination: (Shape, Shape) => Shape, identityElement: Shape)(shape: Shape, nCopies: Int, transformation: Shape => Shape): Shape = {
    def rec(n: Int): Shape = if(n == 0) identityElement else combination(shape, transformation(rec(n - 1)))
    rec(nCopies)
  }

  def unionArray(shape: Shape, nCopies: Int, transformation: Shape => Shape) = array(union, emptySet)(shape, nCopies, transformation)
  def intersectionArray(shape: Shape, nCopies: Int, transformation: Shape => Shape) = array(intersection, wholePlane)(shape, nCopies, transformation)

  // construct few more complicated basic shapes from the primitives
  def ellipse(width: Double, height: Double) = scale(width, height)(unitCircle)
  def circle(radius: Double) = ellipse(radius, radius)
  def regularPolygon(n: Int, innerRadius: Double) = intersectionArray(translate(0, -innerRadius)(upperHalfPlane), n, rotate(2 * math.Pi / n))
  def square(sideLength: Double) = regularPolygon(4, sideLength / 2)
  def triangle = regularPolygon(3, 1)

  // plot function that displays everything in the unit square [0,1]x[0,1]
  def plot(s: Shape) {
    import swing._
    import java.awt.{Graphics2D, Color}
    val frame = new MainFrame() {
      resizable = false;
      contents = new Component() {
        override def paint(g: Graphics2D) {

          val (w, h) = {val dim = this.size; (dim.getWidth, dim.getHeight)}

          g.setColor(Color.BLACK)
          g.fillRect(0, 0, w.intValue, h.intValue);

          g.setColor(Color.WHITE)
          for (x <- 0 until w.intValue; y <- 0 until h.intValue) {
            if(s(x / w, y / h)) {
              g.drawLine(x, y, x, y);
            }
          }
        }
      }
    }
    frame.open();
    frame.size = new java.awt.Dimension(512, 512)
  }

  // construct a gear wheel
  def main(args: Array[String]) {
    val tooth = translate(0, 1.25)(scale(0.1, 0.3)(intersection(triangle, complement(upperHalfPlane))))
    val nTeeth = 12
    val teeth = unionArray(tooth, nTeeth, rotate(2 * math.Pi / nTeeth))
    val wheel = intersection(unitCircle, complement(regularPolygon(6, 0.5)))
    val gearwheel = union(wheel, teeth)
    plot(translate(0.5, 0.5)(scale(0.35, 0.35)(gearwheel)));
}
}