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

* ### 다이어그램 출력 패널 구현 (Canvas Panel) ([코드위치](https://github.com/yangsp31/OLED-Diagram-DrawD_3.0/blob/main/src/DrawFrame/Canvas/canvasPanel.java))
  * JPanel를 기반으로, JComponent의 paintComponent를 Override 하여 사용자로부터 입력받은 데이터를 기반으로 다이어그램을 그려 출력하도록 구현.
  * 각 다이어그램 객체에 할당된 설정값에 따라 출력 형태가 달라지도록 구현.
  * MouseListener 인터페이스를 상속받아 그려진 다이어그램과 Canvas Panel을 마우스로 조작할 수 있도록 구현.
  * 마우스로 다이어그램 조작시 마우스 포인터의 위치에 따라 조작 유형이 선택 되도록 구현.
  * 마우스 클릭시 Mediator 역할을 하는 Frame에게 마우스 좌표에 따른 데이터를 전달하고, 데이터 출력 및 기능 활성화/비활성화를 위해 커스텀 이벤트를 구축하여 사용.
  * 다이어그램은 기본적으로 X, Y 좌표를 기반으로 그려지며, 그려지는 순서에 따라 Z 좌표가 증가하므로, 다이어그램의 레이아웃 위치 설정을 위해 Z 좌표 값에 따라 오름차순 정렬하여 출력하도록 구현.

----

* ### 커스텀 이벤트 구현 ([코드위치](https://github.com/yangsp31/OLED-Diagram-DrawD_3.0/tree/main/src/customEvent))
  * 사용자 조작에 따라 이벤트를 발생시키고, Mediator 역할을 하는 Frame이 수신하여 다른 Panel로 필요한 데이터를 전달할 수 있도록 커스텀 이벤트를 구현.
  * Observer 패턴을 기반으로한 커스텀 이벤트 디스패처를 구현.
<br>

![custom event](https://github.com/user-attachments/assets/52e86b9b-1b03-4ce2-b91d-e7ca45c5f6c4)

----

* ### 재료 검색 구현 ([코드위치](https://github.com/yangsp31/OLED-Diagram-DrawD_3.0/tree/main/src/SearchMaterialFrame))
  * 재료 정보는 System.getProperty("user.dir") + "/materialList" 경로의 하위 폴더 및 파일로 저장되며, 해당 경로를 탐색하여 검색 기능을 구현.
  * 재료 검색 기능은 JTree를 이용하여 디렉토리 및 파일 구조를 트리 형태로 시각화하여 탐색 가능하도록 구현.
  * 전체 디렉토리 구조를 한 번에 메모리에 로드하지 않고, 사용자가 특정 디렉토리를 확장할 때만 해당 하위 항목을 동적으로 불러오는 lazy loading 방식을 적용.
<br><br>

# 회고 & 개선 필요사항/방법 (회고 원문 : [Velog](https://velog.io/@yang_seongp31/))

* ### MVC 패턴의 혼용
  * 각 Frame, Panel, Dialog 내에서 사용되는 컴포넌트 중 이벤트 처리가 필요한 컴포넌트들은 해당 클래스 내부에 EventListener를 함께 구현하는 방식으로 구성되어 있음.
  * 추가적으로, 데이터 및 상태 관리 로직(비즈니스 로직)까지 함께 포함되어 있는 클래스도 존재함.
  * 이러한 구조는 각 구성요소 간의 역할이 분리되지 않는 구조이며 MVC 패턴이 혼용되어 있음이 명확함.
  * 일부 Frame은 Mediator 역할을 수행하면서 Controller와 Model의 기능이 혼합될 수 있으나, 결론적으론 View, Controller, Model의 역할이 모두 혼합된 구조로 구현되어 있음.
  * 따라서, View, Controller, Model의 역할을 최대한 분리하고, Mediator 역할을 하는 Frame 역시 컴포넌트 간 연결과 데이터 전달에만 집중하도록 개선필요.
  * 하지만, JAVA Swing과 AWT의 특성상 완벽한 MVC 패턴을 구성하는것은 한계가 있다고 판단하여, 어느정도의 종속과 MVC 혼용은 불가피 하다고 판단함.
  * 그럼에도, 최대한 MVC 패턴을 명확히 적용함으로써 프로젝트의 유지보수성과 코드 재사용성 상승의 효과가 있을 것으로 판단.
<br><br>

* ### 재료 검색시 데이터 로딩 방식
  * 재료 검색 시, JTree를 활용하여 트리 형태로 구성된 재료 데이터를 제공함.
  * 재료 검색 시, 모든 데이터(파일)를 한 번에 메모리에 로드하는 것은 비효율적이라고 판단.
  * 각각의 데이터(파일)는 복잡하거나 크기가 크지 않지만, 기본적으로 제공받은 재료 데이터는 36개이며, 추가로 저장될 재료 데이터는 최소 수십 개로 확인함.
  * 실제 재료 데이터뿐만 아니라 사용자 커스텀 재료 데이터도 포함하면, 총 데이터(파일) 수가 상당히 많을 것으로 판단함.
  * 따라서 재료 데이터(파일) 전체를 동시에 메모리에 로드하기보다는, 필요할 때마다 로드하는 lazy loading 기법을 적용하는 것이 리소스 효율성 측면에서 경제적 이라고 판단.
  * 데이터의 양이 적을 때는 큰 의미는 없지만, 사용자가 프로그램을 장기간 사용하며 저장 데이터가 누적될수록 이 방식은 유의미 하다고 생각.
<br><br>

* ### 커스텀 이벤트 디스패처
  * Mediator 역할을 하는 Frame에 종속된 Panel에서 발생한 사용자 조작을 기반으로, 다른 Panel이나 기능에 데이터 또는 신호를 전달하기 위해 커스텀 이벤트 디스패처를 구성함.
  * 종속된 Panel이 상위 Frame이나 다른 Panel에 직접 의존하지 않고 데이터를 전달할 수 있는 가장 합리적인 방법이라고 판단함.
  * Observer 패턴을 활용하여 구성했기 때문에, 다양한 클래스에 Listener를 등록할 수 있어 재사용성이 높다고 판단.
  * 하지만 이벤트의 종류나 기능이 많아질수록, 커스텀 이벤트 디스패처의 책임이 과도해져 의도치 않게 중앙 집중적인 구조로 변할 위험이 있다고 생각함.
  * 그럼에도, 현재 프로젝트에서는 종속된 Panel이 상위 Frame이나 다른 컴포넌트에 직접 의존하지 않고 이벤트 신호나 데이터를 전달할 수 있다는 점에서, 커스텀 이벤트 디스패처의 구현과 사용은 합리적인 선택이었다고 판단함.
<br><br>

* ### 개선 필요사항/방법
  * View, Controller, Model의 역할을 최대한 분리하고, Mediator 역할을 하는 Frame 역시 컴포넌트 간 연결과 데이터 전달에만 집중하도록 프로젝트 구조 변경.
  * 프로젝트 구조 재설계 시, 가능하다면 특정 Frame을 Mediator로 두지 않고 별도의 Mediator 클래스로 분리하여 구축하는 방향으로 설계.




