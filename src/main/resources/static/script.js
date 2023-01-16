$(document).ready(function(){

    var fieldName = "productCode";
    var currentPage = 1;
    var sortDirection = "DESC";
    const DESC = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-down-fill" viewBox="0 0 16 16">
                      <path d="M7.247 11.14 2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z"/>
                  </svg>`
    const ASC = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-up-fill" viewBox="0 0 16 16">
                   <path d="m7.247 4.86-4.796 5.481c-.566.647-.106 1.659.753 1.659h9.592a1 1 0 0 0 .753-1.659l-4.796-5.48a1 1 0 0 0-1.506 0z"/>
                 </svg>`

    var paginationId = ['first','previous','current','next','last']

    var oldData = {};

    getAllProducts();

    totalPageCount = 0;
    getTotalPageCount().then(function(data){
        totalPageCount = data;
    }).catch(function(data){
        totalPageCount = 0;
    })

    function updateTotalPageCount(){
        getTotalPageCount().then(function(data){
            totalPageCount = data;
        }).catch(function(data){
            totalPageCount = 0;
        })
    }

    sort(fieldName, sortDirection, currentPage - 1);

    function sort(fieldName, sortDirection, currentPage){
        $.ajax({

            url:"/sort",
            method: "GET",
            data: {"fieldName" : fieldName, "sortDirection" : sortDirection, "currentPage" : currentPage},

            success: function(data){
                clearTableContents();
                var tableContents = '';
                for(var i = 0; i < data.length; i++){
                    tableContents += `<tr>
                                        <td style="font-size: 12px;"> ${data[i].productCode} </td>
                                        <td style="font-size: 12px;"> ${data[i].productDescription} </td>
                                        <td style="font-size: 12px;"> ${data[i].categoryName} </td>
                                        <td style="font-size: 12px;"> ${data[i].price} </td>
                                        <td style="font-size: 12px;"> ${data[i].currency} </td>
                                        <td style="font-size: 12px;"> ${data[i].inventoryAvailable} </td>
                                        <td style="font-size: 12px;"> ${data[i].location} </td>
                                        <td style="font-size: 12px;">
                                            <div class='row'>
                                                <div class='col-1 text-end'>
                                                    <button class='btn btn-danger btn-sm editButton'>Delete</button>
                                                </div>
                                            </div>
                                         </td>
                                    </tr>`
                }
                $('#tbody').html(tableContents);
                $('#tbody').children().each(function(){
                    var data = {
                        'productCode' : $(this).children().eq(0).html().trim(),
                        'categoryName' : $(this).children().eq(2).html().trim(),
                        'price' : $(this).children().eq(3).html(),
                        'currency' : $(this).children().eq(4).html().trim(),
                        'inventoryAvailable' : $(this).children().eq(5).html(),
                        'location' : $(this).children().eq(6).html().trim()
                    }
                    $(this).children().last().children().eq(0).children().on('click','button',function(){
                        removeProduct(data)
                    })

                })
                updateTotalPageCount();
                displayPagination();
            },
            error: function(data){
                console.log(data)
            }
        })
    }

    function getAllProducts(){
        $.ajax({
            url: 'get/products',
            method: 'GET',
            success: function(data){
                console.log(data);
            },
            error: function(data){
                console.log(data);
            }
        })
    }

    function getTotalPageCount(){
        return new Promise(function(resolve,reject){
            $.ajax({
                url: '/count',
                method: 'GET',
                success: function(data){
                    resolve(data);
                },
                error: function(data){
                    reject(data)
                }
            })
        })
    }

    function displayPagination(){
        var htmlContent = '';
        var startPage = currentPage < 3 ? 1 : currentPage - 1;
        var endPage = 2 + startPage;
        endPage = totalPageCount < endPage ? totalPageCount : endPage;
        var diff = startPage - endPage + 2;
        startPage -= startPage - diff > 0 ? diff : 0;

        if(startPage > 1) {
            htmlContent += `<button class='btn btn-light mx-1 btn-sq-responsive btn-sm' style="font-size: 12px;" id='first'>1</button>`
            if(startPage - 1 != 1){
                htmlContent += `<span>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
                                      <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
                                    </svg>
                                </span>`
            }

        }
        for(var i = startPage; i <= endPage; i++){
            if(i == startPage){
                htmlContent += `<button class='btn btn-light mx-1 btn-sq-responsive btn-sm ${i == currentPage ? 'active' : ''}' style="font-size: 12px;" id='previous'>${i}</button>`;
            }else if(i == endPage){
                htmlContent += `<button class='btn btn-light  mx-1 btn-sq-responsive btn-sm ${i == currentPage ? 'active' : ''}' style="font-size: 12px;" id='current'>${i}</button>`;
            }else{
                htmlContent += `<button class='btn btn-light mx-1 btn-sq-responsive btn-sm ${i == currentPage ? 'active' : ''}' style="font-size: 12px;" id='next'>${i}</button>`;
            }
        }
        if(endPage < totalPageCount){
            if(endPage + 1 != totalPageCount){
                htmlContent += `<span>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
                                      <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
                                    </svg>
                                </span>`
            }
            htmlContent += `<button class='btn btn-light mx-1 btn-sq-responsive btn-sm' style="font-size: 12px;" id='last'>${totalPageCount}</button>`
        }

        $('#pagination').html(htmlContent);
    }

    $.each(paginationId, function(i, id){
        $('#pagination').on('click',`#${id}`,function(){
            if($(this).html() != currentPage){
                currentPage = $(this).html();
                sort(fieldName,sortDirection,currentPage - 1);
            }
        })
    })

    $('th').each(function(){
        $(this).on('click',function(){
            resetSortIcon();
            sortDirection = $(this).attr('id') == fieldName ? sortDirection == "DESC" ? "ASC" : "DESC" : "DESC";
            $(this).children().last().children().last().html(sortDirection == "DESC" ? ASC : DESC);
            fieldName = $(this).attr('id');
            sort(fieldName, sortDirection, currentPage - 1);
        })
    })




    function clearTableContents(){
        $("#product_table tbody").empty();
    }

    function resetSortIcon(){
        $('th').each(function(){
            $(this).children().last().children().last().html(DESC);
        })
    }

    $('tbody').on('click','tr',function(event){
        if(!$(event.target).hasClass('editButton')){
            oldData = {
                'oldProductCode' : $(this).children().eq(0).html().trim(),
                'oldProductDescription' : $(this).children().eq(1).html().trim(),
                'oldCategoryName' : $(this).children().eq(2).html().trim(),
                'oldPrice' : $(this).children().eq(3).html().trim(),
                'oldCurrency' : $(this).children().eq(4).html().trim(),
                'oldInventoryAvailable' : $(this).children().eq(5).html().trim(),
                'oldLocation' : $(this).children().eq(6).html().trim()
            }
            populateModal(oldData);
            modalPopup('show','Update')
        }
    })


//    ---------------------------------------------------------------------------------
//      MODAL

//    Trigger Modal
    $('#add').on("click",function(){
        resetModalValues();
        modalPopup('show','Save')
    })

    function resetModalValues(){
        $('#_productCode').val('');
        $('#_categoryName').val('');
        $('#_productDescription').val('');
        $('#_price').val('');
        $('#_currency').val('');
        $('#_inventoryAvailable').val('');
        $('#_location').val('');
    }

    function modalPopup(action,purpose){
        $('#save-edit-button').val(purpose)
        $('#modal').modal(action);
    }

    $('#modalForm').on('submit',function(e){
        e.preventDefault();
        if($('#save-edit-button').val() === 'Save'){
            addNewProduct();
        }else{
            updateProduct(oldData);
        }
        modalPopup('hide','Save');

    })

    function populateModal(data){
        $('#_productCode').val(data.oldProductCode);
        $('#_categoryName').val(data.oldCategoryName);
        $('#_productDescription').val(data.oldProductDescription);
        $('#_price').val(data.oldPrice);
        $('#_currency').val(data.oldCurrency);
        $('#_inventoryAvailable').val(data.oldInventoryAvailable);
        $('#_location').val(data.oldLocation);
    }


//      MODAL
//    ---------------------------------------------------------------------------------



//-----------------------------------------------------------------------------------------

//CRUD

    function addNewProduct(){
        var productCode = $('#_productCode').val()
        var productDescription = $('#_productDescription').val()
        var categoryName = $('#_categoryName').val()
        var price = $('#_price').val()
        var currency = $('#_currency').val()
        var location = $('#_location').val()
        var inventoryAvailable = $('#_inventoryAvailable').val()

        $.ajax({
            url: '/add/product',
            method: 'POST',
            data: {'productCode' : productCode,
                    'productDescription': productDescription,
                    'categoryName' : categoryName,
                    'price' : price,
                    'currency' : currency,
                    'location': location,
                    'inventoryAvailable' : inventoryAvailable
                    },
            success:function(data){
                sort(fieldName,sortDirection,currentPage - 1);
            },
            error: function(data){
                console.log(data);
            }

        })
    }

    function removeProduct(data){
        $.ajax({
            url: '/delete/product',
            method: 'POST',
            data: {"productCode": data.productCode,
                    "categoryName" : data.categoryName,
                    "price": data.price,
                    "currency": data.currency,
                    "inventoryAvailable": data.inventoryAvailable,
                    "location": data.location
                    },
            success: function(data){
                sort(fieldName,sortDirection,currentPage - 1);
            },
            error: function(data){
                console.log(data)
            }
        })
    }

    function updateProduct(oldData){
        $.ajax({
            url:'/update/product',
            method: 'POST',
            data:{
                'oldProductCode' : oldData.oldProductCode,
                'oldProductDescription' : oldData.oldProductDescription,
                'oldCategoryName' : oldData.oldCategoryName,
                'oldPrice' : oldData.oldPrice,
                'oldCurrency' : oldData.oldCurrency,
                'oldInventoryAvailable' : oldData.oldInventoryAvailable,
                'oldLocation' : oldData.oldLocation,
                'productCode' :$('#_productCode').val(),
                'productDescription':$('#_productDescription').val(),
                'categoryName' :$('#_categoryName').val(),
                'price' :$('#_price').val(),
                'currency' :$('#_currency').val(),
                'location':$('#_location').val(),
                'inventoryAvailable' :$('#_inventoryAvailable').val()
            },
            success: function(data){
                sort(fieldName,sortDirection,currentPage - 1);
            },
            error: function(data){
                console.log(data);
            }
        })
    }


//CRUD
//-----------------------------------------------------------------------------------------



})