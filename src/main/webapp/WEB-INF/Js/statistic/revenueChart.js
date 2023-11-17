function getRevenueData(month, DOMelement) {
    fetch("/admin/statistic/customer",{
        method: "POST",
        headers:{
            'Content-Type': "application/json",
        },
        body:JSON.stringify(month.split("-"))
    })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            document.getElementById("dataTable").innerHTML = drawRevenueTable(data[3]);
            return drawRevenueChart(DOMelement, data[3]);
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
        s += `  <tr>
                    <td>` + data.dates[i] + `</td>
                    <td>` + data.orderCount[i] + `</td>
                    <td>` + data.dailyRevenue[i] + `</td>
                </tr>`
    }
    return s;
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