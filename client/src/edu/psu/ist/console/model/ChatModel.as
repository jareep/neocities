package edu.psu.ist.console.model
{
	import com.pnwrain.easyCG.events.ControlEvent;
	import com.pnwrain.easyCG.model.ModelFactory;
	
	import edu.psu.ist.console.value.PlayerChat;
	import edu.psu.ist.console.value.TimelineVO;
	import edu.psu.ist.neocities.model.TimeModel;
	
	import mx.collections.ArrayCollection;

	public class ChatModel extends ModelFactory
	{
		[Bindable]
		public var playerChats :ArrayCollection = new ArrayCollection();
		
		public var totalWords :int = 0;
		public var totalCharacters : int = 0;
		public var totalUtterances : int = 0;
		public var text : String = "";
				
		public function ChatModel()
		{
			super();
			trace("load ChatModel");
		}
		
		public function addUtterance(role : String, utterance:String) : void
		{
			var playerChat :PlayerChat = this.getPlayerChat(role);
			
			var wordArray:Array = utterance.split(' ');
			var wordLen :int = wordArray.length;
			var charCount : int = wordArray.join("").length;
			//this.addWords(wordArray.length);
			//this.addCharacters((wordArray.join("")).length);
									
			this.totalUtterances++;
			this.totalWords+=wordLen;
			this.totalCharacters+=charCount;
			
			if (playerChat == null)
			{
				playerChat = new PlayerChat();
				playerChat.role = role;
				
				this.playerChats.addItem(playerChat);
			}
			
			playerChat.addUtterance();
			playerChat.addCharacters(charCount);
			playerChat.addWords(wordLen);
		}
	
		
		public function getPlayerChat(role : String) :PlayerChat
		{ 
			for each (var pc :PlayerChat in playerChats)
			{//role.toLowerCase().replace(" ", "") == 
				if (pc.role.toLowerCase().replace(" ", "").search(role.toLowerCase().replace(" ", "")) != -1)
				{
					return pc;
				}
				
			}
			
			return null;
		}
		
		public function getChatModel() : String
		{
			var rs : String = "";
			var sModel :SessionModel = ModelFactory.getModel("SessionModel") as SessionModel;
			
			rs += sModel.getSessionHeader();
			rs += ",Utterances,";
			rs += "Words,";
			rs += "Characters\n";
			
			rs += sModel.getSessionInfo() + ",";
			rs += this.totalUtterances.toString() + ",";
			rs += this.totalWords.toString() + ",";
			rs+= this.totalCharacters.toString() + "\n";
			rs += "XXXXXXXXXX\n\n"
			
			rs += "Table:,Chat By Roll\n";
			rs += sModel.getSessionHeader();
			rs +=",Role,Utterances,Word Count,Char Count\n";
			
			for each (var pc : PlayerChat in playerChats)
			{
				rs += sModel.getSessionInfo() + ",";
				rs+= pc.role + ",";
				rs+= pc.utterances + ",";
				rs+= pc.wordCount + ",";
				rs+= pc.characterCount + "\n";
			}
			rs += "XXXXXXXXXX\n"
			rs+="\nTable:,Chat Transcript\n";
			
			var chat :Array = (this.text.replace(","," ")).split("\n");
			
			for each (var line : String in chat)
			{
				if (line != "")
				{
					rs += sModel.getSessionInfo() + ",";
					rs += line + "\n";
				}
			}
			return rs;
		}
		
		public function resetModel () : void
		{
			this.totalCharacters = 0;
			this.totalUtterances = 0;
			this.totalWords = 0;
			
			this.playerChats = new ArrayCollection();
			this.text = "";
		}
	}
}