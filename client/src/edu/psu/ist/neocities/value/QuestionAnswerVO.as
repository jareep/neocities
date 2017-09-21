package edu.psu.ist.neocities.value
{
	[Bindable]
	[RemoteClass(alias="edu.psu.ist.neocities.value.QuestionAnswerVO")]
	public class QuestionAnswerVO
	{
		
		public var label:String;
		public var value:String;
		public var correct:int;
		
		
		public function QuestionAnswerVO()
		{
		}
	}
}