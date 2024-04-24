<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>웹 페이지 예제</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }
    header {
      background-color: #333;
      color: #fff;
      padding: 10px 0;
      text-align: center;
    }
    nav {
      background-color: #f4f4f4;
      padding: 10px;
    }
    nav ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
      text-align: center;
    }
    nav ul li {
      display: inline-block;
      margin-right: 10px;
    }
    .container {
      max-width: 1200px;
      margin: 20px auto;
      padding: 0 20px;
    }
    h2 {
      margin-top: 40px;
    }
    .feature {
      margin-bottom: 20px;
      border: 1px solid #ccc;
      padding: 20px;
      border-radius: 5px;
    }
    .feature img {
      max-width: 100%;
      height: auto;
      margin-top: 10px;
    }
  </style>
</head>
<body>
  <header>
    <h1>웹 페이지 예제</h1>
  </header>
  <nav>
    <ul>
      <li><a href="#post">게시글 올리기</a></li>
      <li><a href="#upload">게시글 사진 업로드</a></li>
      <li><a href="#editor">TOAST UI Editor 글쓰기 페이지</a></li>
      <li><a href="#database">H2 Database</a></li>
      <li><a href="#oauth">인증 OAuth2 구글</a></li>
    </ul>
  </nav>
  <div class="container">
    <section id="post" class="feature">
      <h2>게시글 올리기</h2>
      <p>게시글을 올리는 기능을 제공합니다.</p>
    </section>
    <section id="upload" class="feature">
      <h2>게시글 사진 업로드</h2>
      <p>게시글에 사진을 업로드할 수 있는 기능을 제공합니다.</p>
      <img src="placeholder.jpg" alt="사진 업로드 예시">
    </section>
    <section id="editor" class="feature">
      <h2>TOAST UI Editor 글쓰기 페이지</h2>
      <p>TOAST UI Editor를 사용한 글쓰기 페이지를 제공합니다.</p>
    </section>
    <section id="database" class="feature">
      <h2>H2 Database</h2>
      <p>H2 Database를 활용한 데이터 관리 기능을 제공합니다.</p>
    </section>
    <section id="oauth" class="feature">
      <h2>인증 OAuth2 구글</h2>
      <p>구글 OAuth2를 이용한 인증 기능을 제공합니다.</p>
    </section>
  </div>
</body>
</html>
