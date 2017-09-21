package edu.psu.ist.neocities.oldValue
{
	import com.hurlant.util.der.Integer;
	
	import flash.display.Loader;
	import flash.net.URLRequest;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Image;
	import mx.controls.Text;
	import mx.controls.TextArea;
	
	import spark.components.VGroup;

	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.BriefingVO")]
	public class BriefingVO
	{
		
		public var briefingID : int;
		public var briefing : String;
		public var image : String;
		
		public var alert : String;
		
		public var permissions : ArrayCollection;
		public var dispatchTime : Number;
		
		public var briefingText : TextArea;
		public var briefingImage : Image;
		
		public var briefingGroup : VGroup;
		
		
				
		public function BriefingVO()
		{
			
		}
		
		public function setBriefingView() : void
		{
			this.briefingGroup = new VGroup();
			this.briefingGroup.horizontalAlign = "center";
			this.briefingGroup.percentHeight = 100;
			this.briefingGroup.percentWidth = 100;
			this.setBriefingImage();
			this.setBriefingTextArea();
		}
		
		private function setBriefingImage() : void
		{
			if (image != "")
			{
				this.loadImage();
				
				briefingGroup.addElement(this.briefingImage);
			}
		}
		
		private function setBriefingTextArea() : void
		{
			this.briefingText = new TextArea();
			this.briefingText.editable = false;
			this.briefingText.verticalScrollPolicy = "auto";
			this.briefingText.percentWidth = 100;
			this.briefingText.percentHeight = 100;
		
			this.briefingText.setStyle("borderVisible", false);
			this.briefingText.htmlText = this.briefing;
			
			briefingGroup.addElement(this.briefingText);
		}
		
		private function loadImage() : void
		{
			try
			{
				var loader : Loader = new Loader();
				this.briefingImage = new Image();
				//Security.loadPolicyFile(this.image);
				loader.contentLoaderInfo.addEventListener(Event.COMPLETE, this.setImage);
				
				loader.load(new URLRequest(encodeURI(this.image)));
			}
			catch (e:Error)
			{
				
			}
		}
		
		private function setImage(e:Event) : void
		{
			this.briefingImage.source = e.currentTarget.content;
		}
		
		
	}
}