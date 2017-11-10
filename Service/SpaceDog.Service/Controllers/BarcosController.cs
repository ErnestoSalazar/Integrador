using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace SpaceDog.Service.Controllers
{
    public class BarcosController : ApiController
    {

        private BarcosRepository _barcosRepository = null;
        private UsuariosRepository _usuariosRepository = null;

        public BarcosController(BarcosRepository barcosRepository, UsuariosRepository usuariosRepository)
        {
            _barcosRepository = barcosRepository;
            _usuariosRepository = usuariosRepository;
        }


        public IHttpActionResult Get()
        {
            return Ok(_barcosRepository.GetList());
        }

        public IHttpActionResult Get(int id)
        {
            return Ok(_barcosRepository.Get(id));
        }

        public IHttpActionResult Post(Barco barco)
        {

            var usuario = _usuariosRepository.Get(barco.UsuarioId);

            barco.Usuario = usuario;
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            var barcoModel = new Barco()
            {
                Nombre = barco.Nombre,
                Descripcion = barco.Descripcion,
                Usuario = barco.Usuario
            };

            _barcosRepository.Add(barcoModel);

            barco.Id = barcoModel.Id;
            return Created(
                Url.Link("DefaultApi", new { controller = "Barcos", id = barco.Id }),
                barco
                );
        }

        public IHttpActionResult Put(int id, Barco barco)
        {

            var _barco = new Barco()
            {
                Id = id,
                Nombre = barco.Nombre,
                Descripcion = barco.Descripcion,
                UsuarioId = barco.UsuarioId
            };

            _barcosRepository.Update(_barco);

            return StatusCode(System.Net.HttpStatusCode.NoContent);
        }

        public void Delete(int id)
        {
            _barcosRepository.Delete(id);
        }
    }
}