function getRevenueData(month, DOMelement) {
    fetch("/admin/statistic/customer", {
        method: "POST",
        headers: {
            'Content-Type': "application/json",
        },
        body: JSON.stringify(month.split("-"))
    })
        .then((response) => response.json())
        .then((data) => {
            document.getElementById("dataTable").innerHTML = drawRevenueTable(dataConvert(data[3]));
            return drawRevenueChart(DOMelement, dataConvert(data[3]));
        });
}
function drawRevenueTable(data) {
    let len = data.dailyRevenue.length;
    let s = `<table id="customerTable">
                <thead>
                    <tr>
                        <th>Ngày</th>
                        <th>Số đơn</th>
                        <th>Doanh thu</th>
                    </tr>
                </thead>
            </table>`;
    for (let i = 0; i < len; i++) {
        if(!data.dailyRevenue[i])
            data.dailyRevenue[i] = 0;
        if(!data.orderCount[i])
            data.orderCount[i] = 0;
        s += `  <tr>
                    <td>` + data.dates[i] + `</td>
                    <td>` + data.orderCount[i] + `</td>
                    <td>` + 
                        data.dailyRevenue[i].toLocaleString('it-IT', {style : 'currency', currency : 'VND'}) + 
                    `</td>
                </tr>`
    }
    return s;
}

function dataConvert(data) {
    let date = [];
    let rev = [];
    let cnt = [];
    let s = new Date(new Date(data.dates[0]).getFullYear(),new Date(data.dates[0]).getMonth(),1);
    let e = new Date(new Date(data.dates[0]).getFullYear(),new Date(data.dates[0]).getMonth()+1,1);
    while(s<e){
        date.push(new Date(s).toDateString());
        s.setDate(s.getDate()+1);
    }
    rev.fill(0,0,date.length)
    cnt.fill(0,0,date.length)
    console.log(rev);
    for(let i = 0; i<data.dates.length; i++){
        rev[new Date(data.dates[i]).getDate()-1] = data.dailyRevenue[i];
        cnt[new Date(data.dates[i]).getDate()-1] = data.orderCount[i];
    }
    console.log(date);
    console.log(rev);
    console.log(cnt);
    data.dates = date;
    data.dailyRevenue = rev;
    data.orderCount = cnt;
    return data;

}

function drawRevenueChart(DOMelement, data) {
    new Chart(DOMelement, {
        data: {
            labels: data.dates,
            datasets: [{
                type: 'line',
                label: 'Số đơn',
                data: data.orderCount,
                yAxisID: 'orderCount'
            }, {
                type: 'bar',
                label: 'Doanh thu',
                data: data.dailyRevenue,
                yAxisID: 'dailyRevenue'
            }]
        },
        options: {
            responsive: true,
            scales: {
                orderCount: {
                    type: 'linear',
                    display: true,
                    position: 'left',
                    beginAtZero: true,
                },
                dailyRevenue: {
                    type: 'linear',
                    display: true,
                    position: 'right',
                    beginAtZero: true
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: 'Doanh thu hằng ngày',
                }
            }
        }
    });

}