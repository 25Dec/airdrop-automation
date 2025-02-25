## HƯỚNG DẪN SỬ DỤNG

### 1) Mở file `Main.java` và thay đổi giá trị của các dấu `?`:

#### Trong đó:

- walletPassoword: Là mật khẩu của ví OKX.
- sendToAddress: Là địa chỉ ví cần gửi.
- amountToSend: Là số lượng ETH mà mỗi transaction sẽ gửi.

---

### 2) Chạy trình duyệt ở chế độ CDP:

#### Dành cho `Chrome`:

```shell

cd "C:\Program Files\Google\Chrome\Application" ; .\chrome.exe --remote-debugging-port=9999 --keep-alive --user-data-dir="%LOCALAPPDATA%\Google\Chrome\User Data\Default"
```

#### Dành cho `Edge`:

```shell

cd "C:\Program Files (x86)\Microsoft\Edge\Application" ; .\msedge.exe --remote-debugging-port=8888 --keep-alive
```

---

### 3) Cuối cùng là run code:

- Sử dụng IntelliJ Idea thì nhấn `Shift+F10`

---

### Lưu ý:

- Chỉ hiệu quả với OKX Wallet
- Chỉ hiệu quả với Chrome, Edge
- Khi code đang chạy mà muốn sử dụng trình duyệt để làm việc khác -> Vui lòng sử dụng trình duyệt khác (Brave,
  Firefox, Vivaldi...)