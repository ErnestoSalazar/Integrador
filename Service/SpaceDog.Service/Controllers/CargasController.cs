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


        public IHttpActionResult Post(Carga carga)
        {
            var barco = _barcosRepository.Get(carga.BarcoId);
            carga.Barco = barco;
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var cargaModel = new Carga()
            {
                Cantidad = carga.Cantidad,
                Especie = carga.Especie,
                Temperatura = carga.Temperatura,
                Condicion = carga.Condicion,
                Barco = carga.Barco
            };

            _cargasRepository.Add(cargaModel);

            carga.Id = cargaModel.Id;
            return Created(
                Url.Link("DefaultApi", new { controller = "Cargas", id = carga.Id }),
                carga
                );

        }

        public IHttpActionResult Put(int id, Carga carga)
        {
            var _carga = new Carga()
            {
                Id = id,
                Cantidad = carga.Cantidad,
                Especie = carga.Especie,
                Temperatura = carga.Temperatura,
                Condicion = carga.Condicion,
                BarcoId = carga.BarcoId
            };

            _cargasRepository.Update(_carga);
            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public void Delete(int id)
        {
            _cargasRepository.Delete(id);
        }

    }
}