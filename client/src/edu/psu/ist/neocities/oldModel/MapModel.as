package edu.psu.ist.neocities.oldModel
{
	import com.pnwrain.easyCG.model.ModelFactory;

	import com.esri.aws.awx.geom.PointShape;
	import com.esri.aws.awx.map.layers.overlays.Marker;
	import com.esri.aws.awx.map.layers.overlays.style.ImageMarkerStyle;

	public class MapModel extends ModelFactory
	{
		public function MapModel()
		{
			super();
		}
		
		public function drawMarkers() : void {
           			
			mapMarkers.removeAllOverlays();
			
			var lat : Number;
			var long : Number;
			var marker : Marker;
			var iconPath : String;
								
			for(var i : int = 0; i <= iModel.data.length - 1; i++){
				lat = parseFloat(iModel.data.getItemAt(i).lat);
				long = parseFloat(iModel.data.getItemAt(i).long);
				iconPath = "edu/psu/ist/neocities/assets/event_icons_small/" + iModel.data.getItemAt(i).icon;												
				marker = new Marker(
					new PointShape (iModel.data.getItemAt(i).lat, iModel.data.getItemAt(i).long) , 
					new ImageMarkerStyle (iconPath,8,8) 
				);
				//marker.addEventListener(MouseEvent.CLICK, launchMapInspector(iModel.data.getItemAt(i)) );
				mapMarkers.addOverlay(marker);
			}						
							
   		}
		
	}
}