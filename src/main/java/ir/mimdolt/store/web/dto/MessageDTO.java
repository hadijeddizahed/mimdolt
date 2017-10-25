package ir.mimdolt.store.web.dto;

public class MessageDTO {
  private String field;
  private String message;
  private MessageType type;
  
  public MessageDTO() {
    super();
  }

  public MessageDTO(String field, String message, MessageType type) {
    super();
    this.field = field;
    this.message = message;
    this.type = type;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public MessageType getType() {
    return type;
  }
  
  public void setType(MessageType type) {
    this.type = type;
  }
}
