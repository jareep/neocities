package edu.psu.ist.neocities.util
{
	import mx.controls.Label;
    import mx.controls.dataGridClasses.*;
    import mx.controls.DataGrid;
    import flash.display.Graphics;
    import mx.styles.StyleManager;

    [Style(name="backgroundColor", type="uint", format="Color", inherit="no")]
    
    public class BgColorRenderer extends Label {

        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
		{
		     super.updateDisplayList(unscaledWidth, unscaledHeight);
		 		
		     var g:Graphics = graphics;
		     g.clear();
		     var grid1:DataGrid = DataGrid(DataGridListData(listData).owner);
		     if (grid1.isItemSelected(data) || grid1.isItemHighlighted(data))
		         return;		     		     
		     
		     switch(data[DataGridListData(listData).dataField].toLowerCase()){  
				case 'new' :
					g.beginFill(0xff0000); //RED	
					break;
				
				case 'on route' :
					g.beginFill(0xff6600); //ORANGE	
					break;	
				
				case  'on scene' :  									
					g.beginFill(0xEBC514); //YELLOW			        									   
					break;
				
				case  'complete' :  									
					g.beginFill(0x339933); //GREEN			        									   
					break; 
				
				case 'failed' :  
					g.beginFill(0x000000); //BLACK	
					break;  
					
				default:				
					g.beginFill(0x9999CC); //PURPLE	
					break;
				  
					}				
		     
		      g.drawRect(0, 0, unscaledWidth, unscaledHeight);
			  g.endFill();
		     
		}
		
		override public function set data(value:Object):void
		{
			if(value != null) {
				super.data = value;
				
				switch(data[DataGridListData(listData).dataField].toLowerCase()){  
				
				case  'complete' :  									
					setStyle("color", 0xFFFFFF); //WHITE			        									   
					break; 
				
				case 'failed' :  
					setStyle("color", 0xFFFFFF); //WHITE	
					break;  
					
				default:				
					setStyle("color", 0x000000); //BLACK	
					break;
						
				}
			}
		}//end of function
		
    }//end of class
}