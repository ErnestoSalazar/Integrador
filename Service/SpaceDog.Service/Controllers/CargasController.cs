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
    [Authorize]
    public class CargasController : ApiController
    {
        private CargasRepository _cargasRepository = null;

        public CargasController(CargasRepository cargasRepository, BarcosRepository barcosRepository)
        {
            _cargasRepository = cargasRepository;
        }


        public IHttpActionResult Get()
        {
            var cargas = _cargasRepository.GetList();
            if (cargas.Count <= 0)
            {
                return NotFound();
            }
            return Ok(cargas);
        }

        public IHttpActionResult Get(int id)
        {
            var carga = _cargasRepository.Get(id);
            if(carga == null)
            {
                return NotFound();
            }
            return Ok(carga);
        }


        public IHttpActionResult Post(CargaDto cargaDto)
        {
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