package com.chat.chatingapp.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

        private String sender;
        private String content;
        private String timestamp;
        private MessageType type;

}
