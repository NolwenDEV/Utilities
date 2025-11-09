import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class TextComponentBuilder {
	
	private TextComponent textComponent;
	
		// ---------------------------------------- \\
	
	public TextComponentBuilder() {
		this.textComponent = new TextComponent("");
	}
	
		// ---------------------------------------- \\
	
	public TextComponentBuilder setMessage(String message) {
		textComponent.addExtra(message);
		return this;
	}
	
	public TextComponentBuilder setHoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action hoverAction, String value) {
		textComponent.setHoverEvent(new HoverEvent(hoverAction, new Text(value)));
		return this;
	}
	
	public TextComponentBuilder setClickAction(net.md_5.bungee.api.chat.ClickEvent.Action clickAction, String value) {
		textComponent.setClickEvent(new ClickEvent(clickAction, value));
		return this;
	}
	
		// ---------------------------------------- \\
	
	public TextComponentBuilder compose(TextComponent... components) {		
		for(TextComponent component : components) {
			textComponent.addExtra(component);
		}
		
		return this;
	}
	
	public TextComponent build() { return textComponent; }

}
