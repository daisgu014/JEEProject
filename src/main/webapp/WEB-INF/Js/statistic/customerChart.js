function getCustomerData(month, DOMelement) {
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
            document.getElementById("dataTable").innerHTML = drawCustomerTable(data[0]);
            return drawCustomerChart(DOMelement, data[0]);
        });
}
function drawCustomerTable(data) {
    let len = data.id.length;
    let s = `<table id="customerTable">
                <thead>
                    <tr>
                        <th>Mã Khách Hàng</th>
                        <th>Họ tên</th>
                        <th>Số đơn đã mua</th>
                        <th>Tổng tiền đã mua</th>
                    </tr>
                </thead>
            </table>`;
    for (let i = 0; i < len; i++) {
        s += `  <tr>
                    <td>` + data.id[i] + `</td>
                    <td>` + data.fullName[i] + `</td>
                    <td>` + data.orderCount[i] + `</td>
                    <td>` + data.total[i].toLocaleString('it-IT', {style : 'currency', currency : 'VND'}) + `</td>
                </tr>`
    }
    return s;
}

function drawCustomerChart(DOMelement, data) {
    new Chart(DOMelement, {
        type: 'bar',
        data: {
            labels: data.fullName,
            datasets: [{
                label: 'Số đơn đã mua',
                data: data.orderCount,
                borderWidth: 1,
                yAxisID: 'orderCount'
            }, {
                label: 'Tổng tiền đã mua',
                data: data.total,
                borderWidth: 1,
                yAxisID: 'total'
            }]
        },
        options: {
            responsive: true,
            scales: {
                orderCount: {
                    type: 'linear',
                    display: true,
                    position: 'left',
                },
                total: {
                    type: 'linear',
                    display: true,
                    position: 'right',
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: 'Top 5 Khách hàng mua nhiều nhất',
                }
            }
        }
    });

}