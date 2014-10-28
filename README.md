Month10_AndroidApp_Multithreading
=================================
Small app demo multi threading in Android

- Cũng giống như trong các hệ điều hành khác, trong Android mỗi thread là một đơn vị thực thi song song (concurrent unit of execution).
- Mỗi thread có call stack riêng cho các phương thức được gọi, các tham số và biến địa phương của chúng.
- Mỗi thực thể máy ảo (mỗi máy ảo dành cho 1 tiến trình – một ứng dụng đang chạy), khi được chạy, sẽ có ít nhất một thread chính chạy, thông thường có vài thread khác dành cho các nhiệm vụ phục vụ thread chính.
- Ứng dụng có thể bật các thread bổ sung để phục vụ các mục đích cụ thể.
- Các thread trong cùng một máy ảo tương tác và đồng bộ hóa với nhau qua việc sử dụng các đối tượng dùng chung (shared objects) và các monitor (module kiểm soát việc dùng chung) gắn với các đối tượng này. 

- Có hai cách chính để chạy một thread từ trong mã ứng dụng. 
 + Tạo một lớp mới extend lớp Thread và override phương thức run(). 
 + Tạo một instant mới của lớp Thread với một đối tượng Runnable . 
Trong cả hai cách, cần gọi phương thức start() để thực sự chạy Thread mới.
- Cách tiếp cận của Android đối với các việc tốn thời gian:
Một ứng dụng có thể có một hoạt động tốn thời gian, tuy nhiên, ta muốn UI vẫn đáp ứng tốt đối với các tương tác của người dùng. Android cung cấp hai cách để xử lý tình huống này:
+ Thực hiện thao tác đó trong một service ở background và dùng notification để thông báo cho người dùng về bước tiếp theo
+ Thực hiện thao tác đó trong một background thread.
Các thread của Android tương tác với nhau bằng cách sử dụng (a) các đối tượng Handler và (b) post các đối tượng Runnable tới view chính.
- Handler Class:
+ Khi một tiến trình được tạo cho một ứng dụng, main thread của nó được dành riêng để chạy một message queue, queue này quản lý các đối tượng bậc cao của ứng dụng (activity, intent receiver, v.v..) và các cửa sổ mà chúng tạo ra.
+ Ta có thể tạo các thead phụ, chúng tương tác với thread chính của ứng dụng qua một Handler.
+ Khi ta tạo một Handler mới, nó được gắn với message queue của thread tạo ra nó – từ đó trở đi, nó sẽ gửi các message và các runnable tới message queue đó và thực thi chúng khi chúng ra khỏi message queue.
-Hai ứng dụng chính của Handler: 
 xếp lịch cho các message và runnable cần được thực thi vào thời điểm nào đó trong tương tai, và 
 xếp hàng một action cần thực hiện tại một thread khác

- AsyncTask class:
AsyncTask cho phép sử dụng UI thread một cách dễ dàng và đúng cách. 
AsyncTask cho phép thực hiện các hoạt động background và gửi kết quả cho UI thread mà không phải thao tác với thread và/hoặc handler.
Một tác vụ không đồng bộ (asynchronous task) là một nhiệm vụ tính toán chạy tại một background thread và kết quả sẽ được gửi cho UI thread. 
Một asynchronous task được định nghĩa bởi: Kiểu tổng quát, trạng thái chính, phương thức hỗ trợ


- Đây là một app nhỏ demo tính năng multi threading trong Andoid. App mô phỏng một cuộc đua giữa 2 con ngựa bằng cách hiển thị 2 thanh tiến trình tương ứng với quãng đường mà 2 con ngựa chạy được
- Khi một con ngựa chạy về đích, cuộc đua sẽ kết thúc.

Tài liệu tham khảo: http://developer.android.com/guide/index.html

