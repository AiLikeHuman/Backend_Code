let index = 0;
const track = document.getElementById('bannerTrack');
const banners = document.querySelectorAll('.banner-item');
const totalBanners = banners.length - 1; // 마지막 배너는 첫 번째 배너의 복사본
const adIndicator = document.getElementById('adIndicator');
const progressBar = document.getElementById('progressBar');

function showNextBanner() {
    index++;
    track.style.transform = `translateX(-${index * 100}%)`;

    // 광고 순서 업데이트 (예: 2/3)
    adIndicator.textContent = `${(index % totalBanners) + 1}/${totalBanners}`;

    // 프로그레스 바 리셋 후 다시 애니메이션 시작
    progressBar.style.transition = 'none';  // 애니메이션 리셋
    progressBar.style.width = '0';  // 프로그레스 바를 초기화
    setTimeout(() => {
        progressBar.style.transition = 'width 3s linear';  // 애니메이션 다시 활성화
        progressBar.style.width = '100%';  // 프로그레스 바가 3초 동안 채워짐
    }, 50);  // 약간의 지연을 주어 자연스럽게 애니메이션 시작

    // 마지막 복사본 배너에 도달하면 첫 번째 배너로 돌아가게 함
    if (index === totalBanners) {
        setTimeout(() => {
            track.style.transition = 'none'; // 전환 애니메이션 없이 바로 이동
            index = 0; // 첫 번째 배너로 인덱스 리셋
            track.style.transform = `translateX(0%)`;
            setTimeout(() => {
                track.style.transition = 'transform 0.5s ease'; // 전환 애니메이션 복구
            }, 50); // 잠시 후 전환 애니메이션을 다시 활성화
        }, 500); // 애니메이션이 끝난 후에 설정
    }
}

// 3초마다 광고 전환
setInterval(showNextBanner, 3000);

// 프로그레스 바 초기 설정
progressBar.style.width = '100%';