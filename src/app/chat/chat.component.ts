import { Component, OnInit } from '@angular/core';
import { ChatService } from './chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  searchQuery: string = '';
  messageToSend: string = '';
  userList: string[] = [];
  searchedUserList: string[] = [];
  selectedUser: string = '';
  messages: any[] = [];

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {
    // Fetch the initial list of users when the component is initialized
    this.fetchAllUsers();
  }

  enterNewChat(): void {
    const userName = this.searchQuery.trim();
    if (userName) {
      this.chatService.registration(userName).subscribe(
        (response) => {
          // Handle successful registration
          this.chatService.connectToChat(userName);
          this.selectedUser = userName;
        },
        (error) => {
          if (error.status === 400) {
            alert('Login is already in use!');
          }
        }
      );
    }
  }

  searchUsers(): void {
    // Implement search functionality based on your requirements
    // You may update the user list based on search results
  }

  onSearchUsers(): void {
    if (this.searchQuery.trim() === "") {
      this.searchedUserList = this.userList;
    } else {
      this.searchedUserList = this.userList.filter(user => user.toLowerCase().includes(this.searchQuery.toLowerCase()));
    }
  }

  sendMessage(): void {
    if (this.messageToSend && this.selectedUser) {
      this.chatService.sendMsg("ah john", this.messageToSend, this.selectedUser);
      this.messages.push({ type: 'user', time: new Date().toLocaleString('en-US', { hour: 'numeric', minute: 'numeric', hour12: true }), content: this.messageToSend });
      this.messageToSend = ''; // Clear the input field
    }
  }

  selectUser(userName: string): void {
    this.selectedUser = userName;
    this.chatService.connectToChat(this.selectedUser);
    this.chatService.selectUser(this.selectedUser);
    // Implement logic to load chat history for the selected user
  }

  fetchAllUsers(): void {
    this.chatService.fetchAll().subscribe(
      (users) => {
        this.userList = users;
        this.searchedUserList = this.userList;
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );
  }
}
