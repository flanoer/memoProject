<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="/web/js/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript" charset="utf-8">
const abc = '������';
const bbc = '������';
console.log(abc+' ��' + bbc + '��');

// jsp ������ text interpolation �� �����Ͽ� el tag �� �浹�� �Ͼ.
// ���� �Ϲ����� �������� template literal �� ����� �� ����.

// 1. js ���Ϸ� ���� �����Ѵٸ� template literal �� ���������� ��밡����.
// 2. �Ʒ��� ���� el tag ���ο� js �� ������ �־ ���
// 3. backslash �� jsp el tag�� ���õǵ��� ��
console.log(`111 ${'${abc}'} �� ${'${bbc}'} Ȯ��`);
console.log(`222 \${abc} �� \${bbc} Ȯ��`);

$(document).ready(function(){
	console.log(`${'${abc}'} �� ${'${bbc}'} Ȯ��`);
	console.log(`${1+2} �� 3�̴�`);
});
</script>
</head>
<body>
	
</body>
</html>