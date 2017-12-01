using SpaceDog.Service.Dto;
using SpaceDog.Shared;
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
                return BadRequest(Strings.ENTIDAD_INVALIDA);
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

            var carga = _cargasRepository.Get(id);
            if(carga == null)
            {
                return BadRequest("Carga inexistente");
            }

            carga.BarcoId       = (cargaDto.BarcoId == 0) ? cargaDto.BarcoId : carga.BarcoId;
            carga.Cantidad      = (cargaDto.Cantidad != null && cargaDto.Cantidad > 0) ? cargaDto.Cantidad.Value : carga.Cantidad;
            carga.Especie       = (cargaDto.Especie != null && cargaDto.Especie.ToString().Length > 0) ? cargaDto.Especie.Value : carga.Especie;
            carga.Talla         = (cargaDto.Talla != null && cargaDto.Talla.ToString().Length > 0) ? cargaDto.Talla.Value : carga.Talla;
            carga.Temperatura   = (cargaDto.Temperatura != null) ? cargaDto.Temperatura.Value : carga.Temperatura;
            carga.Condicion     = (cargaDto.Condicion != null) ? cargaDto.Condicion.Value : carga.Condicion;
            

            _cargasRepository.Update(carga);
            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public void Delete(int id)
        {
            _cargasRepository.Delete(id);
        }

    }
}