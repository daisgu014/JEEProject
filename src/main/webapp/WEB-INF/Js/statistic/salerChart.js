
function getSalerData(DOMelement) {
    fetch("/admin/statistic/customer")
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            document.getElementById("dataTable").innerHTML = drawSalerTable(data[1]);
            return drawSalerChart(DOMelement, data[1]);
        });
}
function drawSalerTable(data) {
    let len = data.id.length;

    let s = `
                <table class="h-table" id="salerTable">
                    <thead>
                        <tr>
                            <th>Mã Nhân viên</th>
                            <th>Họ tên</th>
                            <th>Số đơn đã bán</th>
                            <th>Tổng tiền đã bán</th>
                        </tr>
                    </thead>

                </table>`;
    for (let i = 0; i < len; i++) {
        s += `  <tr>
                                <td>` + data.id[i] + `</td>
                                <td>` + data.fullName[i] + `</td>
                                <td>` + data.orderCount[i] + `</td>
                                <td>` + data.total[i] + `</td>
                            </tr>`
    }
    return s;
}

function drawSalerChart(DOMelement, data) {
    new Chart(DOMelement, {
        type: 'bar',
        data: {
            labels: data.fullName,
            datasets: [{
                label: 'Số đơn đã bán',
                data: data.orderCount,
                borderWidth: 1,
                yAxisID: 'orderCount'
            }, {
                label: 'Tổng tiền đã bán',
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
                    text: 'Top 5 Saler tốt nhất',
                }
            }
        }
    });
}

function getCustomerData() {
                const res = fetch("/admin/statistic/customer")
                    .then((response) => response.json())
                    .then((data) => {
                        drawChart(data[0]);
                        document.getElementById("dataTable").innerHTML = drawTable(data[0]);
                    });
            }
            function drawCustomerTable(data) {
                let len = data.id.length;
                let s = "";
                for (let i = 0; i < len; i++) {
                    s += `  <tr>
                                <td>` + data.id[i] + `</td>
                                <td>` + data.fullName[i] + `</td>
                                <td>` + data.orderCount[i] + `</td>
                                <td>` + data.total[i] + `</td>
                            </tr>`
                }
                return s;
            }

            function drawCustomerChart(data) {
                new Chart(ctx, {
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