package edu.psu.ist.neocities.util
{
	import flash.events.ContextMenuEvent;
	import flash.events.Event;
	import flash.system.System;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	
	import mx.controls.Alert;
	import mx.controls.DataGrid;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.EventPriority;
	import mx.events.CloseEvent;
	import mx.events.ListEvent;

	public class IBDataGrid extends DataGrid
	{
		[Bindable] public var enableCopy : Boolean = true;
		// for creating conext menu item for coping functionality				
		private var copyContextItem:ContextMenuItem;		
		// for storing the header text at only once.
		private var headerString : String = '';
		
		private var dataToCopy:String = '';
		public function IBDataGrid()
		{
			super();		
		}
			
		// I am creating a copy context item and its handler in creation complete of DATAGRID if and only if enableCopy is true.
	    override protected function createChildren():void{
			super.createChildren();
			 var flag:Boolean = false
				if(enableCopy){
								contextMenu = new ContextMenu();
								createContextMenu();
							    addEventListener(ListEvent.ITEM_CLICK,
				                         										itemClickHandler,
				                         										false, EventPriority.DEFAULT_HANDLER);
				                 flag = true;
   					}			
		}
	    
		private function createContextMenu():void{
		      copyContextItem = new ContextMenuItem("copy row/s");
	          copyContextItem.enabled = false;
			  copyContextItem.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT,copyDataHandler);		
			  contextMenu.customItems.push(copyContextItem);
			  // comment the following line if you want default items in context menu.
			  contextMenu.hideBuiltInItems();
		}
		
		private function copyDataHandler(event:Event):void{
			dataToCopy = '';
			if(selectedItems != null){
				 dataToCopy = getSelectedRowsData();
				 dataToCopy = ((headerString == '') ? getHeaderData() : headerString)+"\n" + dataToCopy;				
				 copyContextItem.enabled = true;
				 System.setClipboard(dataToCopy);
			}  			
		}
		
		private function handleAlertClose(event:CloseEvent):void{
			trace("handling .. the event");
			if(event.detail == 1)
			{		
			  	 
			}
			 
		}
		private function getHeaderData():String{		 
			   headerString = '';		
					for(var j:int = 0; j< columnCount; j++){
						if((columns[j] as DataGridColumn).visible)
							headerString += (columns[j] as DataGridColumn).headerText +"\t";
					}
		 		return headerString;	 	
		}	
		
		private function getSelectedRowsData():String{
			var rowsData : String = '';
			for(var i:int =0;i<selectedItems.length;i++) {
				for(var j:int = 0; j< columnCount; j++){
					if((columns[j] as DataGridColumn).visible)
						rowsData += selectedItems[i][(columns[j] as DataGridColumn).dataField] +"\t";
				}
				rowsData+= "\n";							 
			}
			return rowsData;
		}
	    
	    private function itemClickHandler(event:ListEvent):void
	    {
	    	copyContextItem.enabled = true;
	    }		
	}
}