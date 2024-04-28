<p align="center">
  <img src="img/logo.png" alt="Your Image" width="400">
</p>

## <center>National College of Ireland
### <center>HDip in Computing
### <center> Distributed Systems
<br>

#### <center> Domain: smart education
#### <center> Student name: Mingyan Jia
#### <center> Student number: 22239227
#### <center> HDip in Science in Computing
#### <center> Lecturer: Mark Cudden
#### <center> Academic year: 2023/24

# <center> Part 1
## 1. Domain Description

For my smart classroom scenario, I have four rpc services to track students attendance(simple rpc), <br>
random arrangement of presentation(server side stream rpc),bidirectional chatting prompt(bidirectional rpc)
and CCTV systems(client side stream rpc). The overall aim of the app is designed to provide convenience for<br>
students and teachers, so that all four services I picked are very common used.

Attendance Tracking Service: <br>
Integrating with students to get student's name sent to server's recording csv file, with essential<br>
information as student name and the clocked in time (the time is server side time), so that Tutors can<br>
easily know the exact problem of attendance. (Also, I have another service based on the attended students)<br>

Chat Service: <br>
It is another common usage in class, students can easily send questions to tutor and get responses during lessons.<br>

Presentation Management Service: <br>
Managing the order of student presentations, ensuring a smooth transition between speakers and providing the<br>
instructor with control over the session flow.<br>

CCTV Service:<br>
Ensuring a safe and secure learning environment is important, and the CCTV Service plays<br>
a crucial role in achieving this objective.<br>

In my case project, attendance service is the most important one, as it not only gets the information<br>
of students' attendance but also provides presentation service basic data, in real scenario, tutors might<br>
manage random presentation during class(so the exact attended students are necessary). chat service is also playing<br>
an essential role especially in a big classroom with much more students. Lastly, CCTV service usually<br>
runs all the day (I made it simple in my case, but certainly, it ain't simple ).

## 2. Service definition and RPC
Attendance Tracking Service

RPC Methods: TrackAttendance<br>
Records a student's attendance.
Request: AttendanceRequest contains studentName.
Response: AttendanceResponse includes message confirming registration.

ListAttendance: Retrieves attendance for a specific session.
Request: ListRequest specifies the sessionDate.
Response: AttendanceList provides a list of students and their attendance status.


Chat Service

    RPC Methods:
        SendMessage: Allows sending messages between users.
            Request: MessageRequest includes senderId, receiverId, and messageText.
            Response: MessageResponse confirms the message delivery with status.
        ReceiveMessage: Stream for receiving messages.
            Stream Request: N/A
            Stream Response: MessageStream outputs incoming messages in real-time.

Presentation Queue Management Service

    RPC Methods:
        AddToQueue: Adds a student to the presentation queue.
            Request: QueueRequest includes studentId and presentationTopic.
            Response: QueueResponse confirms addition with queuePosition.
        StartPresentation: Triggers the start of the next presentation.
            Request: StartRequest specifies session controls.
            Response: StartResponse provides details on the next presenter.
