let htmlRow = "";
let idVideo = null;

$(function () {
  senarioPadrao();
  
  $("#MUSICAL").click(function () {
    $.get(`${$("#context").val()}videos/categoria/${$(this).text()}`, function (data) {
      senarioComBusca(data);
    });
  });
  
  $("#RELIGIOSO").click(function () {
    $.get(`${$("#context").val()}videos/categoria/${$(this).text()}`, function (data) {
      senarioComBusca(data);
    });
  });
  
  $("#EDUCATIVO").click(function () {
    $.get(`${$("#context").val()}videos/categoria/${$(this).text()}`, function (data) {
      senarioComBusca(data);
    });
  });
  
  $("#POLEMICO").click(function () {
    $.get(`${$("#context").val()}videos/categoria/${$(this).text()}`, function (data) {
      senarioComBusca(data);
    });
  });
  
  $("#OUTROS").click(function () {
    $.get(`${$("#context").val()}videos/categoria/${$(this).text()}`, function (data) {
      senarioComBusca(data);
    });
  });
  
  $("#btnPesquisarVideo").click(function () {
    if($("#pesquisar").val()) {
      $("#main").empty();
      $.get(`${$("#context").val()}videos/filtrodos/`+$("#pesquisar").val(), function (data) {
        senarioComBusca(data);
      });
    } else {
      senarioPadrao();
    }
  });
});

function senarioPadrao() {
  $.get(`${$("#context").val()}todos/videos`, function (data) {
    if (data && data.length === 0) {
      $("#spanMensagem").text("Nenhum vídeo encontrado!");
    } else {
      $("#spanMensagem").text("Últimos vídeos");
    }
    if(data.length > 4) {
      $("#main").empty();
      let videos = sliceIntoChunks(data, 4);
      for(let i = 0; i < videos.length; i++) {
        htmlRow = "<div class='row'>";
        for(let j = 0; j < videos[i].length; j++) {
          let video = videos[i];
          htmlRow = htmlRow + `
            <div class="col-sm-12 col-md-6 col-lg-3 col-xl-3 col-xxl-3 p-2">
              <div class="card">
                <div class="card-header">
                  ${video[j].categoria}
                </div>
                <div class="card-body">
                  <video id="${video.id}" onplay="videoClick(this)" width="100%" height="240px;" max-height="240px;" controls>
                    <source src="https://res.cloudinary.com/storedrinks/video/upload/v1683476933/${video[j].url}" type="video/mp4">
                    Your browser does not support the video tag.
                  </video>
                  <div class="row">
                    <div class="col-sm-12">
                      <span>${formatDate(video[j].dataPublicacao)}</span>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-sm-12">
                      <span title="${video[j].descricao}" class="fw-semibold text-truncate d-inline-block" style="max-width: 310px;">${video[j].descricao}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>`;
        }
        htmlRow += "</div>";
        $("#main").append(htmlRow);
        htmlRow = "";
      }
    } else {
      $("#main").empty();
      htmlRow = "<div class='row'>";
      $.each(data, function (i, video) {
        htmlRow = htmlRow + `
          <div class="col-sm-12 col-md-6 col-lg-3 col-xl-3 col-xxl-3 p-2">
            <div class="card">
              <div class="card-header">
                ${video.categoria}
              </div>
              <div class="card-body">
                <video id="${video.id}" onplay="videoClick(this)" width="100%" height="240px;" max-height="240px;" controls>
                  <source src="https://res.cloudinary.com/storedrinks/video/upload/v1683476933/${video.url}" type="video/mp4">
                  Your browser does not support the video tag.
                </video>
                <div class="row">
                  <div class="col-sm-12">
                    <span>${video.dataPublicacao}</span>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <span title="${video.descricao}" class="fw-semibold text-truncate d-inline-block" style="max-width: 310px;">${video.descricao}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>`;
      });
      htmlRow += "</div>";
      $("#main").append(htmlRow);
    }
  });
}

function senarioComBusca(data) {
  if(data && data.length === 0) {
    $("#spanMensagem").text("Nenhum vídeo encontrado!");
  } else {
    $("#spanMensagem").text("Últimos vídeos");
  }
  if (data.length > 4) {
    let videos = sliceIntoChunks(data, 4);
    for (let i = 0; i < videos.length; i++) {
      htmlRow = "<div class='row'>";
      for (let j = 0; j < videos[i].length; j++) {
        let video = videos[i];
        htmlRow = htmlRow + `
        <div class="col-sm-12 col-md-6 col-lg-3 col-xl-3 col-xxl-3 p-2">
          <div class="card">
            <div class="card-header">
              ${video[j].categoria}
            </div>
            <div class="card-body">
              <video onplay="videoClick(this)" id="${video.id}" width="100%" height="240px;" max-height="240px;" controls>
                <source src="https://res.cloudinary.com/storedrinks/video/upload/v1683476933/${video[j].url}" type="video/mp4">
                Your browser does not support the video tag.
              </video>
              <div class="row">
                <div class="col-sm-12">
                  <span>${video[j].dataPublicacao}</span>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-12">
                  <span title="${video[j].descricao}" class="fw-semibold text-truncate d-inline-block" style="max-width: 310px;">${video[j].descricao}</span>
                </div>
              </div>
            </div>
          </div>
        </div>`;
      }
      htmlRow += "</div>";
      $("#main").append(htmlRow);
    }
  } else {
    $("#main").empty();
    htmlRow = "<div class='row'>";
    $.each(data, function (i, video) {
      htmlRow = htmlRow + `
      <div class="col-sm-12 col-md-6 col-lg-3 col-xl-3 col-xxl-3 p-2">
        <div class="card">
          <div class="card-header">
            ${video.categoria}
          </div>
          <div class="card-body">
            <video id="${video.id}" onplay="videoClick(this)" width="100%" height="240px;" max-height="240px;" controls>
              <source src="https://res.cloudinary.com/storedrinks/video/upload/v1683476933/${video.url}" type="video/mp4">
              Your browser does not support the video tag.
            </video>
            <div class="row">
              <div class="col-sm-12">
                <span>${video.dataPublicacao}</span>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <span title="${video.descricao}" class="fw-semibold text-truncate d-inline-block" style="max-width: 310px;">${video.descricao}</span>
              </div>
            </div>
          </div>
        </div>
      </div>`;
    });
    htmlRow += "</div>";
    $("#main").append(htmlRow);
  }
}

function videoClick(e) {
  let atualIdVideo = $(e).get(0).id;
  if(idVideo === null) {
    idVideo = $(e).get(0).id;
  } else if (idVideo !== atualIdVideo) {
    $("#"+idVideo).get(0).pause();
    $("#"+idVideo).get(0).currentTime = 0;
    idVideo = atualIdVideo;
  }
  //console.log($(e).get(0).duration);
}

function sliceIntoChunks(arr, chunkSize) {
  const res = [];
  for (let i = 0; i < arr.length; i += chunkSize) {
    const chunk = arr.slice(i, i + chunkSize);
    res.push(chunk);
  }
  return res;
}

function formatDate(stringDate) {
  return new Date(stringDate).toLocaleDateString("pt-BR");
}