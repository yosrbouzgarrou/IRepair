<?php

namespace App\Controller;

use App\Entity\Abonnement;
use App\Entity\Facture;
use App\Entity\Materiel;
use App\Entity\Offre;
use App\Form\FactureType;
use App\Form\OffreType;
use App\Repository\FactureRepository;
use App\Repository\MaterielRepository;
use App\Repository\OffreRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;


/**
 * @Route("/factures")
 */
class FactureController extends AbstractController
{

    /**
     * @Route("/all", name="facture_all", methods={"GET"})
     */
    public function allAction(FactureRepository $factureRepository,Request $request, PaginatorInterface $paginator)
    {
        $factures = [];


        $facturess = $factureRepository->findAll();
        $factures = $paginator->paginate(
            $facturess,
            $request->query->getInt('page', 1),
            1
        );

        return $this->render('facture/index.html.twig', array(
            'factures' => $factures,
        ));
    }

    /**
     * @Route("/new", name="app_facture_new", methods={"GET", "POST"})
     */
    public function new(Request $request, FactureRepository $factureRepository): Response
    {
        $facture = new Facture();
        $form = $this->createForm(FactureType::class, $facture);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $factureRepository->add($facture);
            return $this->redirectToRoute('app_facture_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('facture/new.html.twig', [
            'facture' => $facture,
            'd' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idFact}", name="app_facture_show", methods={"GET"})
     */
    public function show($idFact, FactureRepository $factureRepository): Response
    {
        $facture = $factureRepository->findOneById($idFact);
        return $this->render('facture/show.html.twig', [
            'facture' => $facture,
        ]);
    }

    /**
     * @Route("/edit/{id}", name="facture_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Facture $facture, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(FactureType::class, $facture);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('facture_all', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('facture/editFact.html.twig', [
            'facture' => $facture,
            'form' => $form->createView(),
        ]);
    }

    ///**
    // * @Route("/edit/{id}", name="app_facture_edit",methods={"GET","POST"})
    // */
    //public function modifFact(Request $request, $id): Response
    //{
    //    $facture = $this->getDoctrine()->getManager()->getRepository(Facture::class)->find($id);

    //    $form = $this->createForm(FactureType::class, $facture);

    //    $form->handleRequest($request);

    //    if ($form->isSubmitted() && $form->isValid()) {
    //        $em = $this->getDoctrine()->getManager();
    //        $em->flush();

    //        return $this->redirectToRoute('facture_all');
    //    }

    //    return $this->render('facture/edit.html.twig', ['f' => $form->createView(), 'facture' => $facture]);


    //}

    /**
     * @Route("/{idFact}", name="app_facture_delete", methods={"POST"})
     */
    public function delete($idFact, FactureRepository $factureRepository): Response
    {
        $facture = $factureRepository->find(intval($idFact));
        if ($facture)
            $factureRepository->remove($facture);

        return $this->redirectToRoute('facture_all', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/mobile/facts", name="facts_mobile", methods={"GET"})
     */
    public function mobile(FactureRepository $factureRepository): Response
    {
        $activity = $this->getDoctrine()->getRepository(Facture::class)->findAll();
        $dt = array();
        foreach ($activity as $key => $cat) {
            $dt[$key]['id'] = $cat->getId();
            $dt[$key]['materiel_id'] = $cat->getMateriel()->getId();
            $dt[$key]['prix_Totale'] = $cat->getPrixTotale();


            // $dt[$key]['images'] = $cat->getDureExercice();

        }
        return new JsonResponse($dt);
    }

    /**
     * @Route("/mobile/newFact",name="newfactureMobile", methods={"POST"})
     * @throws \Exception
     */
    public function newFactureMobile(Request $request, EntityManagerInterface $entityManager): Response
    {
        $facture = new Facture();
        var_dump($request->get("materiel_id"));
        $matRepo = $entityManager->getRepository(Materiel::class);
        $mat = $matRepo->find($request->get("materiel_id"));
        $facture->setMateriel($mat);
        $facture->setPrixTotale($request->get("prix_Totale"));


        $entityManager->persist($facture);
        $entityManager->flush();

        return new JsonResponse($facture);

    }

    /**
     * @Route("/mobile/updateFact/{id}",name="updateFactMobile", methods={"POST"})
     * @throws \Exception
     */
    public function updateFactMobile(Request $request, FactureRepository $factureRepository, $id, EntityManagerInterface $entityManager): Response
    {
        $facture = $this->getDoctrine()->getRepository(Facture::class)->find($id);

        if ($request->get("materiel_id")) {
            $facture->setMateriel($entityManager->getRepository(Materiel::class)->find($request->get("materiel_id")));
        }
        $facture->setPrixTotale(($request->get("prix_totale")));

        $entityManager->persist($facture);
        $entityManager->flush();
        return new JsonResponse("{status: 'updated'}", Response::HTTP_OK);


    }

    /**
     * @Route("/mobile/delete/{id}", name="fact_delete", methods={"POST"})
     */
    public function deleteFact(Request $request, Facture $facture, EntityManagerInterface $entityManager): Response
    {
        $entityManager->remove($facture);
        $entityManager->flush();


        return new JsonResponse("{status: 'deleted'}", Response::HTTP_OK);
    }

}
