/**
 *  날짜 데이터 연동
 */
 	//날짜를 변수화
	const date = new Date();
	console.log(date);
	let year = date.getFullYear();  //2024
	let month = '0' + date.getMonth() + 1; //01 011
	month = month.substring(1)   //substring(인덱스)인덱스부터 끝까지 추출
	let day = '0' + date.getDate(); //025
	day = day.substring(1);

	let today = year + month + day;
    $.ajax({
       type: "GET",
       url: "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=Ekmt%2F9o9LH7HCquGXEIc3o%2BqAAtZfnTGEYWIfUhvcoCyVWLiJLAWujWkNctV06dlz5c0oaKZ1rft2llpEDl%2BTw%3D%3D&pageNo=1&numOfRows=1000&dataType=JSON&base_date=" + today + "&base_time=0600&nx=55&ny=127",
       success: function(data){
          console.log(data);
          console.log(data.response.body.items.item[3].obsrValue);
          let item = data.response.body.items.item[3];
          let content = "날짜: " + item.baseDate + 
             ", 발표시각: " + item.baseTime + 
             ", 기온: " + item.obsrValue;

          $('.result').text(content);
       },
       error: function(error){
          console.log(error);
       }
    });