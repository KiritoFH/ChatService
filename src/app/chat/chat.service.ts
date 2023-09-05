import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// import SockJS from 'sockjs-client';
// import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
declare var SockJS: any;


@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private url = 'http://localhost:8080';

  private stompClient: any;
  public selectedUser: string;
  private newMessages: Map<string, string> = new Map();
  constructor(private http: HttpClient) {}
  

  connectToChat(userName: string): void {
    console.log('connecting to chat...');
    let socket = new SockJS(this.url + '/chat');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, (url: string) => {
      console.log('connected to: ' + url);
      this.stompClient.subscribe('/topic/messages/' + userName, (response: { body: string; }) => {
        let data = JSON.parse(response.body);
        console.log("####", data);
        if (this.selectedUser === data.fromLogin) {
          // Render the message in your chat UI
          this.render(data.message, data.fromLogin);
          console.log('trying loop data:' + data.message);
        } else {
          this.newMessages.set(data.fromLogin, data.message);
          // Update UI to indicate new messages
          this.updateUIForNewMessage(data.fromLogin, data.message);
          console.log('new message:' + data.message);
        }
      });
    });
  }

  sendMsg(from: string, text: string, to:string): void {
    this.stompClient.send('/app/chat/' + this.selectedUser, {}, JSON.stringify({
      fromLogin: from,
      message: text,
      to: to
    }));
    console.log('sendMsg(): ' + text);
    // save to db (from, text, to, (seen/not seen))
  }
  
  // Enter the service fee by me

  // Send accept / Reject service might be just me send noti

  //Reschedule call yu hui

  registration(userName: string): Observable<any> {
    return this.http.get(`${this.url}/registration/${userName}`);
  }

  selectUser(userName: any): void {
    this.selectedUser = userName;
    const isNew = this.newMessages.has(userName);
  
    if (isNew) {
      // Remove the notification
      this.newMessages.delete(userName);
  
      // Render the new message in your chat UI
      const newMessage = this.newMessages.get(userName);
      if (newMessage !== undefined) {
        this.render(newMessage, userName);
      }
    }
  
    // Update the UI for the selected user
    this.updateUIForSelectedUser(userName);
  }
  

  fetchAll(): Observable<string[]> {
    return this.http.get<string[]>(`${this.url}/fetchAllUsers`);
  }

  private render(message: string, userName: string): void {
    const chatHistoryElement = document.getElementById('chat-history'); // Adjust this to match your actual HTML structure
    if (chatHistoryElement) {
      const messageElement = document.createElement('div');
      messageElement.innerText = `${userName}: ${message}`;
      chatHistoryElement.appendChild(messageElement);
    }
  }
  

  private updateUIForNewMessage(userName: string, message: string): void {
    // Implement a notification or any other UI update for new messages here
  }
  

  private updateUIForSelectedUser(userName: string): void {
    const selectedUserElement = document.getElementById('selected-user'); // Adjust this to match your actual HTML structure
    if (selectedUserElement) {
      selectedUserElement.innerText = `Chat with ${userName}`;
    }
  
    // Clear notifications or update other UI elements here
  }
  
}


