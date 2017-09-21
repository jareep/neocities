package edu.psu.ist.console.value
{
	[Bindable]
	public class PlayerChat
	{
		public var role:String;
		public var wordCount :int = 0;
		public var utterances : int = 0;
		public var characterCount : int = 0;
		
		public function PlayerChat()
		{
		}
		
		public function addUtterance() : void
		{
			
			utterances++;

			
		}
		
		public function addWords(num : int) : void
		{
			wordCount+=num;
		}
		
		public function addCharacters(num : int) : void
		{
			characterCount += num;
		}
		
		
	}
}