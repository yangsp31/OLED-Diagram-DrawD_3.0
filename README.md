# DrawD 3.0 - OLED 다이어그램 작성 응용 프로그램 (Window application)

### 응용 프로그램 다운 : [Google Drive](https://drive.google.com/file/d/1WPZiPO9TlY1bY0WjswT-ytspWFo-7x5C/view?usp=drive_link)

* 해당 응용 프로그램은 사용자의 그 어떠한 정보도 수집하거나 사용하지 않으며, 사용자의 결과물을 이용하거나 타인에게 공개하지 않습니다.
* 해당 응용 프로그램은 악성 코드를 포함하지 않으며, 사용자의 PC에 위해를 가하지 않습니다.
* 한국저작권위원회에 비상업적 목적으로 컴퓨터프로그램저작물 / 응용프로그램으로 저작권이 등록되어 있습니다.
<br>

* 2025.02.21. - 2025.03.13.
* 1인 프로젝트로 진행.
<br><br><br>

# 개요

* 디스플레이 분야, 특히 OLED를 연구하는 대학원생의 연구 및 문서 작업 효율성을 높이기 위한 응용 프로그램
* 사람이 손으로 그린 도식보다 정밀하고 객관적인 다이어그램을 작성할 수 있도록 지원하며, 실험 설계 단계나 연구 내용을 설명할 때 시각적 근거 자료로 활용될 수 있도록 개발
* 동일한 기능을 제공하는 웹 서비스( https://do.drawd.store )를 운영 중이지만, 사용자의 심리적 보안 우려와 서비스 중단 시 기능 제공의 지속성을 고려하여 데스크톱 응용 프로그램을 별도로 개발
* 별도의 프레임 워크없이, JAVA만을 사용하여 개발됨
<br><br>

# Architecture

### simple ver.
<br>

![DrawD3 0 Simple ARC](https://github.com/user-attachments/assets/c6e82ea9-df21-46dc-b32d-87cf935bee03)
<br><br>

### full ver.
<br>

![DrawD3 0 ARC](https://github.com/user-attachments/assets/d525f41a-9d23-4244-bb34-b578d4721351)
<br><br>

# Screen

![image](https://github.com/user-attachments/assets/8bbe8ec4-7442-4536-9b04-7d21e2cbf692)
![image](https://github.com/user-attachments/assets/e7143f4c-fe78-4235-b483-e6e1e7f8f6a3)
![image](https://github.com/user-attachments/assets/7962c542-74a4-4014-8519-ce069af7d17d)
![image](https://github.com/user-attachments/assets/bd9b193f-329d-4db9-9a74-7a38201483ee)
![image](https://github.com/user-attachments/assets/850a4cc9-d736-471f-9933-8f2f0e0c627b)
![image](https://github.com/user-attachments/assets/cafbc3ee-4c0d-49f4-b2bd-cd980ac29dd4)
<br><br>

# 사용기술

* ### JAVA (JDK 21.0.5)
  * JAVA에서 기본적으로 제공하는 Swing과 AWT만으로도 요구사항에 맞는 GUI 윈도우 응용프로그램이 개발 가능하다 판단하여 사용
  * 윈도우 응용프로그램 개발 시 .NET 8이나 Electron을 사용할 수도 있었지만, 새로운 기술 사용에 따른 학습 비용을 줄이고 빠른 개발을 위해 선택

* ### GSON (JSON)
  * 다양한 종류의 데이터를 저장해야 하는 프로젝트 요구 사항에 따라, XML보다 간편하고 직관적인 JSON 포맷을 사용하기 위해 선택
<br><br>

# 주요 개발내역

* ### 다이어그램 출력 패널 (Canvas Panel) ([코드위치](https://github.com/yangsp31/DrawD_Next.js/blob/main/src/app/(page)/newD/%5Buuid%5D/page.tsx))
  * JPanel를 기반으로, JComponent의 paintComponent를 Override 하여 사용자로부터 입력받은 데이터를 기반으로 다이어그램을 그려 출력하도록 구현.
  * 각 다이어그램 객체에 할당된 설정값에 따라 출력 형태가 달라지도록 구현.
  * MouseListener 인터페이스를 상속받아 그려진 다이어그램과 Canvas Panel을 마우스로 조작할 수 있도록 구현.
  * 마우스로 다이어그램 조작시 마우스 포인터의 위치에 따라 조작 유형이 선택 되도록 구현.
  * 마우스 클릭시 Mediator 역할을 하는 Frame에게 마우스 좌표에 따른 데이터를 전달하고, 데이터 출력 및 기능 활성화/비활성화를 위해 커스텀 이벤트를 구축하여 사용.
  * 다이어그램은 기본적으로 X, Y 좌표를 기반으로 그려지며, 그려지는 순서에 따라 Z 좌표가 증가하므로, 다이어그램의 레이아웃 위치 설정을 위해 Z 좌표 값에 따라 오름차순 정렬하여 출력하도록 구현.










