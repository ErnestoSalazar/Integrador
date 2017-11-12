using SpaceDog.Service.Dto;
using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace SpaceDog.Service.Controllers
{
    public class CargasController : ApiController
    {
        private CargasRepository _cargasRepository = null;
        private BarcosRepository _barcosRepository = null;

        public CargasController(CargasRepository cargasRepository, BarcosRepository barcosRepository)
        {
            _cargasRepository = cargasRepository;
            _barcosRepository = barcosRepository;
        }


        public IHttpActionResult Get()
        {
            return Ok(_cargasRepository.GetList());
        }

        public IHttpActionResult Get(int id)
        {
            return Ok(_cargasRepository.Get(id));
        }


        public IHttpActionResult Post(CargaDto cargaDto)
        {
            var barco = _barcosRepository.Get(cargaDto.BarcoId);
            cargaDto.Barco = barco;
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var cargaModel = cargaDto.ToModel();

            _cargasRepository.Add(cargaModel);

            cargaDto.Id = cargaModel.Id;
            return Created(
                Url.Link("DefaultApi", new { controller = "Cargas", id = cargaDto.Id }),
                cargaDto
                );

        }

        public IHttpActionResult Put(int id, CargaDto cargaDto)
        {
            var _carga = cargaDto.ToModel();
            _carga.BarcoId = cargaDto.BarcoId;
            _carga.Id = id;

            _cargasRepository.Update(_carga);
            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public void Delete(int id)
        {
            _cargasRepository.Delete(id);
        }

    }
}