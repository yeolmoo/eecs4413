// replace-api.js
const fs = require('fs');
const path = require('path');

const directory = './src'; // 프론트엔드 소스 디렉토리
const localURL = 'http://ec2-34-202-116-9.compute-1.amazonaws.com:8080';
const ec2URL = 'http://localhost:8080';

// 재귀적으로 .js, .jsx, .ts, .tsx 파일 찾기
function replaceInFiles(dir) {
  fs.readdirSync(dir).forEach(file => {
    const fullPath = path.join(dir, file);
    const stat = fs.statSync(fullPath);

    if (stat.isDirectory()) {
      replaceInFiles(fullPath);
    } else if (/\.(js|jsx|ts|tsx)$/.test(file)) {
      let content = fs.readFileSync(fullPath, 'utf-8');
      if (content.includes(localURL)) {
        const replaced = content.replaceAll(localURL, ec2URL);
        fs.writeFileSync(fullPath, replaced);
        console.log(`✅ Updated: ${fullPath}`);
      }
    }
  });
}

replaceInFiles(directory);
console.log('🎉 API 주소 바꾸기 완료!');
