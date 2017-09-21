package edu.psu.ist.neocities.value
{
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.QuestionHistoryVO;
	import edu.psu.ist.neocities.model.TimeModel;
	
	import flash.display.Loader;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.URLRequest;
	import flash.system.Security;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.controls.LinkButton;
	import mx.core.UIComponent;
	
	import spark.components.RadioButton;
	import spark.components.RadioButtonGroup;
	import spark.components.TextArea;
	import spark.components.TextInput;
	import spark.components.VGroup;
	import spark.events.TextOperationEvent;
	import spark.utils.TextFlowUtil;
	
	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.QuestionVO")]
	public class QuestionVO
	{		
		
		public var questionID:int;
				
		public var label:String;
		public var type:String;
		public var image:String = "";
		public var answers:ArrayCollection;
		
		public var questionLabel:TextArea;
		public var questionImage:Image;
		
		public var length:int = 0;
		public var answer:QuestionAnswerVO;
		public var textAnswerValue:String;
			
		public var uiQuestion:VGroup;
		public var radioButtonGroup:ArrayCollection;
		public var textAnswer:TextInput;
		
		public var qhVO :QuestionHistoryVO;
		
		
		[Bindable]
		private var tModel : TimeModel = ModelFactory.getModel("TimeModel") as TimeModel;
		
		public function QuestionVO()
		{
			answer = new QuestionAnswerVO();
			qhVO = new QuestionHistoryVO;
			qhVO.answerValue = "{n/a}";
			answer.correct = 0;
			answer.value = "";
		}
		
		public function getQuestionUI() : UIComponent
		{
			this.uiQuestion = new VGroup();	
			this.uiQuestion.horizontalAlign = "center";
			this.uiQuestion.width = 400;
			this.setQuestionLabel();
			this.setQuestionImage();
			this.setAnswerUI();
			
			return this.uiQuestion;
		}
		
		private function setQuestionLabel():void
		{
			this.questionLabel = new TextArea();
			
			this.questionLabel.textFlow = TextFlowUtil.importFromString(this.label);
			//this.questionLabel.htmlText = this.label;
			this.questionLabel.width = 400;
			this.questionLabel.heightInLines = NaN;
			this.questionLabel.editable = false;
			this.questionLabel.setStyle("borderVisible", false);
		

			uiQuestion.addElement(this.questionLabel);
		}
		
		private function setQuestionImage() : void
		{
			if (this.image !=  "")
			{
				this.loadImage();
				
				uiQuestion.addElement(this.questionImage);
								
			}
		}
		
		private function setAnswerUI():void
		{
			switch (this.type.toUpperCase())
			{
				case "MC":
					//Alert.show("multiple choice");
					this.setRadioAnswer();
					//Alert.show(this.length.toString());
					var questionGroup :VGroup = new VGroup();
					questionGroup.horizontalAlign = "left";
					for(var x:int = 0; x < this.length; x++)
					{
						questionGroup.addElement(this.radioButtonGroup.getItemAt(x) as RadioButton);
					}
					
					this.uiQuestion.addElement(questionGroup);
					break;
				case "SA":
					//return "textbox";
					this.setTextBox();
					break;
				case "INTRO":
					//this.addTimer();
					break;
			}
			
			return;
			
		}
		
		private function setTextBox():void
		{
			this.textAnswer = new TextInput();
			this.textAnswer.addEventListener(TextOperationEvent.CHANGE, setTextAnswer);
			this.uiQuestion.addElement(this.textAnswer);
		}
		
		private function setRadioAnswer():void
		{
			var rbG = new RadioButtonGroup();
			
			this.radioButtonGroup = new ArrayCollection();
			for each (var answer:Object in this.answers)
			{
				
				var rbA:RadioButton = new RadioButton();
				rbA.label =(answer as QuestionAnswerVO).label;
				rbA.value = (answer as QuestionAnswerVO);
				rbA.group = rbG;
				rbA.addEventListener(MouseEvent.CLICK, setSelectedAnswer);
				
				this.radioButtonGroup.addItem(rbA);
				this.length++;		
			}
			
		}
		
		private function setTextAnswer(event:Event):void{
			this.qhVO.answerTime = tModel.masSec;
			this.qhVO.answerValue = this.textAnswer.text;
			//this.textAnswerValue = this.textAnswer.text;
			//Alert.show("Blah Blah Blah " +this.answer);
		}
		
		private function setSelectedAnswer(event:Event):void
		{
			this.qhVO.answerTime = tModel.masSec;
			this.answer = (event.currentTarget as RadioButton).value as QuestionAnswerVO;
			this.qhVO.answerValue = this.answer.value;	
			this.qhVO.correct = this.answer.correct;
			//Alert.show(this.selectedAnswer);
		}
		
		private function loadImage() : void
		{
			var loader : Loader = new Loader();
			this.questionImage = new Image();
			//Security.loadPolicyFile(this.image);
			loader.contentLoaderInfo.addEventListener(Event.COMPLETE, this.setImage);
			
			loader.load(new URLRequest(encodeURI(this.image)));
			
		}
		
		private function setImage(e:Event) : void
		{
			this.questionImage.source = e.currentTarget.content;
		}
	}
}