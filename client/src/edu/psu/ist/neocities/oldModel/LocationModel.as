package edu.psu.ist.neocities.oldModel
{
	public class LocationModel
	{
		import com.pnwrain.easyCG.model.ModelFactory;
		
		import com.esri.aws.awx.geom.PointShape;
		import com.esri.aws.awx.map.layers.overlays.Marker;
		import com.esri.aws.awx.map.layers.overlays.style.ImageMarkerStyle;
		
		public function LocationModel() extends ModelFactory
		{
			super();
			trace("load LocationModel");
		}
		
	}// end of class
}// end of package